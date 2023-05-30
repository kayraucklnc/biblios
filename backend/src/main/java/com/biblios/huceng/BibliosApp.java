package com.biblios.huceng;

import com.biblios.huceng.entity.Role;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.author.service.AuthorService;
import com.biblios.huceng.usecases.book.service.BookService;
import com.biblios.huceng.usecases.campus.service.CampusService;
import com.biblios.huceng.usecases.following.service.FollowingService;
import com.biblios.huceng.usecases.profile.service.ProfileService;
import com.biblios.huceng.usecases.publisher.service.PublisherService;
import com.biblios.huceng.usecases.rate.service.RatingService;
import com.biblios.huceng.usecases.review.service.ReviewService;
import com.biblios.huceng.usecases.series.service.SeriesService;
import com.biblios.huceng.usecases.shelf.service.ShelfService;
import com.biblios.huceng.usecases.signup.service.SignupService;
import com.biblios.huceng.util.DummyDataController;
import com.biblios.huceng.util.RoleUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class BibliosApp {

    public static void main(String[] args) {
        SpringApplication.run(BibliosApp.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Allow requests from the localhost:3000 to pass through to Spring Security
     * Cross-Origin mapping.
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * This code will be executed before the app starts.
     * Ideal place to initialize starting users for testing.
     * Also, admins have to be added here since they are predetermined.
     *
     * @return
     */
    @Bean
    CommandLineRunner runner(StartupService service,  ProfileService profileService,
                             RatingService ratingService,
                             SignupService signupService,
                             BookService bookService,
                             AuthorService authorService,
                             PublisherService publisherService,
                             CampusService campusService,
                             ShelfService shelfService,
                             ReviewService reviewService,
                             SeriesService seriesService
    ) {
        return args -> {
            service.saveRole(new Role(null, RoleUtil.ROLE_ADMIN));
            service.saveRole(new Role(null, RoleUtil.ROLE_LIBRARIAN));
            service.saveRole(new Role(null, RoleUtil.ROLE_STUDENT));
            service.saveRole(new Role(null, RoleUtil.ROLE_DONOR));

            DummyDataController dummyDataController = new DummyDataController(
                    service,
                    profileService,
                    ratingService,
                    signupService,
                    reviewService,
                    bookService,
                    authorService,
                    publisherService,
                    campusService,
                    shelfService,
                    seriesService

            );
            dummyDataController.createDummyData();
        };
    }
}
