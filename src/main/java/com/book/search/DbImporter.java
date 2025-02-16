package com.book.search;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.Buffer;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class DbImporter {
    
    private static final String CSV_URL = "https://gist.githubusercontent.com/hhimanshu/d55d17b51e0a46a37b739d0f3d3e3c74/raw/5b9027cf7b1641546c1948caffeaa44129b7db63/books.csv";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/library";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin123";

    public static void main(String[] args) {
        try{
            // step1: download the csv data
            System.out.println("Downloading CSV data from " + CSV_URL);
            InputStream csvStream = downloadCSV(CSV_URL);

            // step2: parse the csv data
            System.out.println("Parsing CSV data");
            List<String[]> records = parseCSV(csvStream);

            // step3: save the data to the database
            System.out.println("Saving data to the database");
            insertData(records);

            System.out.println("Data import completed successfully");


        }
        catch(IOException | CsvValidationException e){
            System.err.println("Error downloading data: " + e.getMessage());
        }
        catch(SQLException e){
            System.err.println("Error saving data to the database: " + e.getMessage());
        }
    }

    private static List<String[]> parseCSV(InputStream csvStream) throws IOException, CsvValidationException {
        List<String[]> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));

        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();

        CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

        String[] record;
        try {
            while ((record = csvReader.readNext()) != null) {
                records.add(record);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        csvReader.close();
        return records;
    }


    private static InputStream downloadCSV(String url) throws IOException {
        URI uri = URI.create(url);
        URL csvUrl = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) csvUrl.openConnection();
        return connection.getInputStream();
    }

    // Insert data into the database, inlcuding the authors and book_authors tables
    private static void insertData(List<String[]> records) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        String insertAuthorsSQL = "INSERT INTO authors (name) VALUES (?) ON CONFLICT DO NOTHING RETURNING author_id";
        String selectAuthorSQL = "SELECT author_id FROM authors WHERE name = ?";
        String insertBookAuthorSQL = "INSERT INTO books_authors (book_id, author_id) VALUES (?, ?) ";
        String insertBookSQL = "INSERT INTO books (title,rating,description,language,isbn,book_format,edition,pages,publisher,publish_date,first_publish_date,liked_percent,price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING book_id";

        conn.setAutoCommit(false);
        try{
            PreparedStatement insertAuthorsStmt = conn.prepareStatement(insertAuthorsSQL);
            PreparedStatement selectAuthorStmt = conn.prepareStatement(selectAuthorSQL);
            PreparedStatement insertBookAuthorStmt = conn.prepareStatement(insertBookAuthorSQL);
            PreparedStatement insertBookStmt = conn.prepareStatement(insertBookSQL);

            // if (records.get(0)[0].equals("bookId")) {
            //     for (int i = 0; i < records.size(); i++) {
            //         String[] row = records.get(i);
            //         // Create a new array excluding the first column
            //         records.set(i, Arrays.copyOfRange(row, 1, row.length));
            //     }
            // }
            

            String[] headers = records.get(0);
            Map<String, Integer> headerMap = new HashMap<>();
            for(int i = 0; i < headers.length; i++){
                headerMap.put(headers[i], i);
                System.out.println(headers[i] + " : " + i);
            }

            for (int i = 1; i < records.size(); i++){
                String[] record = records.get(i);
                String title = record[headerMap.get("title")];
                String rating = record[headerMap.get("rating")];
                String description = record[headerMap.get("description")];
                String language = record[headerMap.get("language")];
                String isbn = record[headerMap.get("isbn")];
                System.out.println("isbn: " + isbn);
                String bookFormat = record[headerMap.get("bookFormat")];
                System.out.println("bookFormat: " + bookFormat);
                String edition = record[headerMap.get("edition")];
                int pages = Integer.parseInt(record[headerMap.get("pages")]);
                String publisher = record[headerMap.get("publisher")];
                String publishDateStr = record[headerMap.get("publishDate")];
                String firstPublishDateStr = record[headerMap.get("firstPublishDate")];
                Double likedPercent = Double.parseDouble(record[headerMap.get("likedPercent")]);
                Double price = Double.parseDouble(record[headerMap.get("price")]);
                String authorName = record[headerMap.get("author")];


                // insert into authors 
                int authorId;
                // String authorName = authors.split(",")[0].trim();
                insertAuthorsStmt.setString(1, authorName);
                ResultSet authorRS = insertAuthorsStmt.executeQuery();
                if(authorRS.next()){
                    authorId = authorRS.getInt("author_id");
                }else{
                    // Author already exists, retieve the author_id
                    selectAuthorStmt.setString(1, authorName);
                    ResultSet selectAuthorRS = selectAuthorStmt.executeQuery();
                    if(selectAuthorRS.next()){
                        authorId = selectAuthorRS.getInt("author_id");
                    }else{
                        throw new SQLException("Author not found");
                    }
                    selectAuthorRS.close();
                    
                }
                authorRS.close();


                // insert into books
                int bookId;
                insertBookStmt.setString(1, title);
                insertBookStmt.setDouble(2, Double.parseDouble(rating));
                insertBookStmt.setString(3, description);
                insertBookStmt.setString(4, language);
                insertBookStmt.setString(5, isbn);
                insertBookStmt.setString(6, bookFormat);
                insertBookStmt.setString(7, edition);
                insertBookStmt.setInt(8, pages);
                insertBookStmt.setString(9, publisher);

                // handle null values
                Date publishDate = null;
                Date firstPublishDate = null;
                try{
                    publishDate = Date.valueOf(publishDateStr);
                }
                catch(IllegalArgumentException e){
                    System.err.println("Invalid publish date: " + publishDateStr);
                }
                try{
                    firstPublishDate = Date.valueOf(firstPublishDateStr);
                }
                catch(IllegalArgumentException e){
                    System.err.println("Invalid first publish date: " + firstPublishDateStr);
                }
                insertBookStmt.setDate(10, publishDate);
                insertBookStmt.setDate(11, firstPublishDate);

                insertBookStmt.setDouble(12, (likedPercent));
                insertBookStmt.setDouble(13, (price));
                ResultSet bookRS = insertBookStmt.executeQuery();
                if(!bookRS.next()){
                    throw new SQLException("Book not inserted");
                }
                else{
                    bookId = bookRS.getInt("book_id");
                    bookRS.close();
                }

                

                //insert into book_authors
                insertBookAuthorStmt.setInt(1, bookId);
                insertBookAuthorStmt.setInt(2, authorId);
                insertBookAuthorStmt.executeUpdate();
            }
            conn.commit();
        }
        catch(SQLException e){
            conn.rollback();
            throw e;
        }
        finally{
            conn.setAutoCommit(true);
            conn.close();
        }

    }

}
