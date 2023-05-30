package com.biblios.huceng.util;

import com.biblios.huceng.bibliosentity.*;
import com.biblios.huceng.usecases.author.service.AuthorService;
import com.biblios.huceng.usecases.campus.service.CampusService;
import com.biblios.huceng.usecases.publisher.service.PublisherService;
import com.biblios.huceng.usecases.series.service.SeriesService;
import com.biblios.huceng.usecases.shelf.service.ShelfService;
import com.biblios.huceng.usecases.review.service.ReviewService;
import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.Review;
import com.opencsv.exceptions.CsvValidationException;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.util.ResourceUtils;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

import java.io.*;

import com.biblios.huceng.entity.*;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.book.service.BookService;
import com.biblios.huceng.usecases.following.service.FollowingService;
import com.biblios.huceng.usecases.profile.dto.ProfileRequest;
import com.biblios.huceng.usecases.profile.service.ProfileService;
import com.biblios.huceng.usecases.rate.service.RatingService;
import com.biblios.huceng.usecases.signup.service.SignupService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
public class DummyDataController {
    public static final String LEVENTOGLU = "leventoglu";
    public static final String KAYRAUCKILINC = "kayrauckilinc";
    public static final String OZGUROKUMUS = "ozgurokumus";
    public static final String DAVUTKULAKSIZ = "davutkulaksiz";
    public static final String NIKOLA_DRLJACA = "nikolaDrljaca";


    private SeriesService seriesService;
    private ShelfService shelfService;
    private CampusService campusService;
    private PublisherService publisherService;
    private AuthorService authorService;
    private BookService bookService;
    private StartupService service;
    private ProfileService profileService;
    private RatingService ratingService;
    private SignupService signupService;
    private FollowingService followingService;

    private ReviewService reviewService;

    private Random random;


    public DummyDataController(StartupService service,
                               ProfileService profileService,
                               RatingService ratingService,
                               SignupService signupService,
                               ReviewService reviewService,
                               BookService bookService,
                               AuthorService authorService,
                               PublisherService publisherService,
                               CampusService campusService,
                               ShelfService shelfService,
                               SeriesService seriesService) {
        this.service = service;
        this.profileService = profileService;
        this.ratingService = ratingService;
        this.signupService = signupService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.campusService = campusService;
        this.shelfService = shelfService;
        this.reviewService = reviewService;
        this.seriesService = seriesService;
    }


    public void createDummyData() throws IOException {
        this.random = new Random();

        createUsers();
        createProfile();
        createCampus("/campus_data.csv");
        createShelf("/shelf_data.csv");
        createAuthors("/author_data.csv");
        createPublishers("/publisher_data.csv");
        createBooks("/main_dataset.csv");
        createReviews();
        createBorrows();
        createSeries();
    }

    private void createBorrows() {
        AppUser appUser = service.getUser(KAYRAUCKILINC);
        bookService.borrowBook(978089042555L, appUser.getId());
    }

    Hashtable<String, Campus> campuses = new Hashtable<>();
    Hashtable<String, Shelf> shelvesForBook = new Hashtable<>();
    Hashtable<String, Publisher> publishersForBook = new Hashtable<>();
    Hashtable<String, Author> authorsForBook = new Hashtable<>();

    public void createSeries() throws IOException {
        List<Series> allSeries = new ArrayList<Series>();
        List<String> seriesNames = new ArrayList<String>();

        seriesNames.add("Harry Potter");
        seriesNames.add("Lord of the Rings");
        seriesNames.add("the Hobbit");
        seriesNames.add("Hunger Games");

        for(int i = 0;i<seriesNames.size();i++) {
            int tempID = i+1;
            Series series = new Series(String.valueOf(tempID),seriesNames.get(i));
            allSeries.add(series);
        }

        seriesService.createAllSeries(allSeries);
    }

    public void createShelf(String csvFile) throws IOException {
        List<Shelf> allShelf = new ArrayList<Shelf>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream(csvFile)))) {
            reader.readNext();
            String[] line;
            int counter = 1;
            int choose = 1;
            while ((line = reader.readNext()) != null) {
                if (counter % 30 == 0) {
                    choose++;
                }
                String[] data = line;
                Campus c = campuses.get(String.valueOf(choose));
                Shelf s = new Shelf(data[0], data[1], data[2], c);
                allShelf.add(s);
                counter++;
                shelvesForBook.put(data[0], s);

            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        shelfService.createAllShelves(allShelf);
    }

    public void createCampus(String csvFile) throws IOException {
        List<Campus> allCampus = new ArrayList<Campus>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream(csvFile)))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line;

                Campus c = new Campus(data[0], data[1]);
                allCampus.add(c);
                campuses.put(data[0], c);

            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        campusService.createAllCampus(allCampus);
    }


    public void createPublishers(String csvFile) throws IOException {
        List<Publisher> allPublishers = new ArrayList<Publisher>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream(csvFile)))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line;

                Publisher a = new Publisher(data[0], data[1], data[2], data[3]);
                allPublishers.add(a);
                publishersForBook.put(data[0], a);

            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        publisherService.createAllPublishers(allPublishers);
    }

    private void createReviews() {

            Book book = bookService.getBook(9781509858637L);
            Review review = new Review(3.0f, createRandomTime(), book);
            reviewService.save(review);

            Book book2 = bookService.getBook(9780199689903L);
            Review review2 = new Review(4.0f, createRandomTime(), book2);
            reviewService.save(review2);



    }


    public void createAuthors(String csvFile) throws IOException {
        List<Author> allAuthors = new ArrayList<Author>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream(csvFile)))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line;

                Author a = new Author(data[0], data[1], data[2], data[3]);
                allAuthors.add(a);
                authorsForBook.put(data[0], a);

            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


        authorService.createAllAuthors(allAuthors);

    }


    public void createBooks(String csvFile) throws IOException {


        List<Book> allBooks = new ArrayList<Book>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream(csvFile)))) {


            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] data = line;
                String tempLong = data[4].substring(0, data[4].length() - 3);
                Long tempisbn = Long.parseLong(tempLong);
                int lastThreeDigitsSum = 0;
                int count = 0;
                long number = tempisbn;

                while (number > 0 && count < 3) {
                    long digit = number % 10; // Get the last digit
                    lastThreeDigitsSum += digit; // Add the digit to the sum
                    number /= 10; // Remove the last digit from the number
                    count++; // Increase the count
                }
                Lorem lorem = LoremIpsum.getInstance();
                String descriptionParagraph = lorem.getParagraphs(1, 1);

                //random string generator
                /*int leftLimit = 97; // letter 'a'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 10;
                Random random = new Random();
                StringBuilder buffer = new StringBuilder(targetStringLength);
                for (int i = 0; i < targetStringLength; i++) {
                    int randomLimitedInt = leftLimit + (int)
                            (random.nextFloat() * (rightLimit - leftLimit + 1));
                    buffer.append((char) randomLimitedInt);
                }
                String generatedString = buffer.toString();*/
                Double tempRate = Double.parseDouble(data[3]);
                if (lastThreeDigitsSum == 0) {
                    lastThreeDigitsSum += 10;
                } else if (lastThreeDigitsSum <= 3) {
                    lastThreeDigitsSum = 4;
                }
                // Create a new instance of the Random class
                Random rand = new Random();

                // Generate a random number between 1 and 129 (inclusive)

                Shelf tempShelf = shelvesForBook.get(data[7]);
                Publisher tempPublisher = publishersForBook.get(data[8]);
                Book b = new Book(tempisbn, data[1], data[2], data[6], data[0], lastThreeDigitsSum - 3, lastThreeDigitsSum, data[5], descriptionParagraph, tempRate, tempShelf, tempPublisher);
                b.getAuthors().add(authorsForBook.get(data[6]));
                allBooks.add(b);


            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }


        bookService.createAllBooks(allBooks);


    }

    private void createPendingUsers() {
        createSinglePendingUser("Pending", "User", "mail@mail.com", "pendinguser", "1234", RoleUtil.ROLE_ADMIN);
        for (int i = 0; i < 8; i++) {
            int num = 2 + i;
            createSinglePendingUser("Pending" + num, "User" + num, "mail" + num + "@mail.com", "pendinguser" + num, "1234", RoleUtil.ROLE_STUDENT);
        }
        for (int i = 0; i < 5; i++) {
            int num = 10 + i;
            createSinglePendingUser("Pending" + num, "User" + num, "mail" + num + "@mail.com", "pendinguser" + (num), "1234", RoleUtil.ROLE_ACADEMICIAN);
        }
        for (int i = 0; i < 3; i++) {
            int num = 15 + i;
            createSinglePendingUser("Pending" + num, "User" + num, "mail" + num + "@mail.com", "pendinguser" + (num), "1234", RoleUtil.ROLE_GRADUATE);
        }
    }

    private void createSinglePendingUser(String first_name, String last_name, String email, String username, String password, String role) {
        PendingUser user = new PendingUser(
                null,
                first_name,
                last_name,
                email,
                username,
                password,
                new ArrayList<>()
        );
        signupService.saveUserAsPending(user);
        signupService.assignRoleToPendingUser(user.getUsername(), role);
    }

    private void setRates() {
        ratingService.rateProfileByUsername(KAYRAUCKILINC, LEVENTOGLU, 3.0);
        ratingService.rateProfileByUsername(OZGUROKUMUS, LEVENTOGLU, 5.0);
        ratingService.rateProfileByUsername(DAVUTKULAKSIZ, LEVENTOGLU, 0.0);

        ratingService.rateProfileByUsername(KAYRAUCKILINC, DAVUTKULAKSIZ, 4.0);

        ratingService.rateProfileByUsername(LEVENTOGLU, KAYRAUCKILINC, 2.0);
        ratingService.rateProfileByUsername(DAVUTKULAKSIZ, KAYRAUCKILINC, 5.0);

        ratingService.rateProfileByUsername(DAVUTKULAKSIZ, NIKOLA_DRLJACA, 1.0);
    }

    private void createUsers() {
        createAdminUsers();
//        createDummyUsers();
    }

    private void createAdminUsers() {
        ArrayList<AppUser> appUsers = new ArrayList<AppUser>();
        appUsers.add(new AppUser(null, "Nikola", "Drljaca", "drljacan@outlook.com", "nikolaDrljaca", "1234", new ArrayList<>()));
        appUsers.add(new AppUser(null, "Kayra", "Uckilinc", "kayrauckilinc1@gmail.com", "kayrauckilinc", "1234", new ArrayList<>()));
        appUsers.add(new AppUser(null, "Davut", "Kulaksiz", "kulaksiz@gmail.com", "davutkulaksiz", "1234", new ArrayList<>()));
        appUsers.add(new AppUser(null, "Safa", "Leventoglu", "leventoglu@gmail.com", "leventoglu", "1234", new ArrayList<>()));
        appUsers.add(new AppUser(null, "Özgür", "Okumuş", "ozgurokumus@gmail.com", "ozgurokumus", "1234", new ArrayList<>()));


        for (AppUser appUser : appUsers) {
            service.saveUser(appUser);
        }

        service.assignRoleToUser("nikolaDrljaca", RoleUtil.ROLE_DONOR);
        service.assignRoleToUser("kayrauckilinc", RoleUtil.ROLE_ADMIN);
        service.assignRoleToUser("davutkulaksiz", RoleUtil.ROLE_LIBRARIAN);
        service.assignRoleToUser("leventoglu", RoleUtil.ROLE_STUDENT);
        service.assignRoleToUser("ozgurokumus", RoleUtil.ROLE_ADMIN);
    }












    private void createProfile() {
//        1-10 range
//        photo: "./assets/post/2.jpg"
//        photo: "./assets/person/2.png"
//        photo: "./assets/banner/2.jpg"
        profileService.updateProfile(KAYRAUCKILINC, new ProfileRequest("./assets/person/1.png", "./assets/banner/1.jpg", "I experiment and share"));
        profileService.updateProfile(OZGUROKUMUS, new ProfileRequest("./assets/person/3.png", "./assets/banner/8.jpg", "I wish I could have more time to play MMOs"));
        profileService.updateProfile(DAVUTKULAKSIZ, new ProfileRequest("./assets/person/2.png", "./assets/banner/6.jpg", "Wannabe Web Developer"));
        profileService.updateProfile(LEVENTOGLU, new ProfileRequest("./assets/person/6.png", "./assets/banner/5.jpg", "Your Favorite Discord Admin"));
        profileService.updateProfile(NIKOLA_DRLJACA, new ProfileRequest("./assets/person/7.png", "./assets/banner/3.jpg", "Student at Hacettepe University in Ankara, Turkey."));
    }



    // Creates time from current time with random minutes.
    private Date createPastTime(int years, int months, int days, int hours) {
        return this.createPastTime(years, months, days, hours, this.random.nextInt(0, 60));
    }

    // Creates a time past from current time.
    private Date createPastTime(int years, int months, int days, int hours, int setMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -years);
        cal.add(Calendar.MONTH, -months);
        cal.add(Calendar.DAY_OF_YEAR, -days);
        cal.add(Calendar.HOUR, -hours);
        cal.set(Calendar.MINUTE, setMinutes);

        return cal.getTime();
    }

    // Creates a random time in last 2 weeks.
    private Date createRandomTime() {
        int max = 1000 * 60 * 60 * 24 * 14; // 2 weeks
        int min = 1000 * 60; // 1 minute
        return new Date(System.currentTimeMillis() - (this.random.nextInt(max + 1 - min) + min));
    }
}
