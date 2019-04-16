package com.epam.hrynyshyn.constants;

import java.io.File;

public class Constants {
    public static class Services {
        public static final String USER_SERVICE = "userService";
        public static final String PRODUCT_SERVICE = "productService";
        public static final String CATEGORY_SERVICE = "categoryService";
        public static final String MANUFACTURER_SERVICE = "manufacturerService";
        public static final String ORDER_SERVICE = "orderService";
    }

    public static class Captcha {
        public static final String CAPTCHA_STORING_MODE = "captchaStoringMode";
        public static final String CAPTCHA_PROVIDER = "captchaProvider";
        public static final String CAPTCHA_CLEANER_TIMEOUT = "captchaCleanerTimeout";
        public static final String CAPTCHA = "captcha";
        public static final String SESSION_STORING = "session";
        public static final String COOKIE_STORING = "cookie";
        public static final String HIDDEN_STORING = "hidden";
        public static final String CAPTCHA_ID = "captchaId";
        public static final Character[] CAPTCHA_SYMBOLS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public static final int CAPTCHA_LENGTH = 6;
        public static final int CAPTCHA_LIFETIME = 60;
        public static final String CAPTCHA_TIMEOUT = "captchaTimeout";
    }

    public static class ErrorMessages {
        public static final String EMPTY_NAME = "Please provide your name";
        public static final String EMPTY_LAST_NAME = "Please provide your last name";
        public static final String EMPTY_EMAIL = "Please provide your email";
        public static final String INCORRECT_EMAIL = "Please provide correct email like myCoolEmail@example.com";
        public static final String RESERVED_EMAIL = "Sorry, user with this email already exists";
        public static final String EMPTY_PASSWORD = "Please provide your password";
        public static final String INCORRECT_PASSWORD = "Please provide correct password, longer than 8 symbols, use numbers and letters";
        public static final String NOT_MATCH = "Passwords doesn't match";
        public static final String INCORRECT_CAPTCHA = "Inputted data is incorrect";
        public static final String EMAIL_NOT_EXISTS = "Not existed email";
        public static final String PASSWORD_NOT_CORRECT = "Incorrect password";
        public static final String TRANSACTION_ROLLBACK = "Query executing failure, rollback";
        public static final String TRANSACTION_FAILURE = "Transaction failure";
        public static final String STATEMENT_PREPARE_FAILURE = "Failure during preparing connection statement";
        public static final String PRODUCT_NOT_FOUND_BY_ID = "Product with specified number not found";
        public static final String INCORRECT_LOCALE_STORAGE_MODE = "Incorrect locale storage mode";
        public static final String SECURITY_CONFIGURATION_FAILURE = "Security configuration failure";
    }

    public static class User {
        public static final String ID="id";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String USER_NAME = "userName";
        public static final String USER_AVATAR = "userAvatar";
        public static final String USER = "user";
        public static final String USERS = "users";
        public static final String SOURCE_PAGE_URL = "sourcePageUrl";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String IMAGE_MIME_TYPE = "image/png";

    }
    public static class Role{
        public static final String ROLE_ID="role_id";
        public static final String ROLES="roles";
        public static final String ROLE="role";
    }

    public static class Validation {
        public static final String REGISTRATION_TIMEOUT = "registrationTimeout";
        public static final String CONFIRM_PASSWORD = "confirmedPassword";
        public static final String ERRORS = "errors";
        public static final String FORM_DATA = "formData";
        public static final String AVATARS_STORAGE_PATH = String.format("%1$savatars%1$s", File.separator);
        public static final String AVATARS_EXTENSION = ".png";
    }

    public static class Product {
        public static final String PRODUCTS = "products";
        public static final String MANUFACTURERS = "manufacturers";
        public static final String CATEGORIES = "categories";
        public static final String MIN_PRICE = "minPrice";
        public static final String MAX_PRICE = "maxPrice";
        public static final String PRODUCTS_PER_PAGE = "productsPerPage";
        public static final String CURRENT_PAGE = "currentPage";
        public static final String PRODUCTS_COUNT = "productsCount";
        public static final String NUMBER_OF_PAGES = "numberOfPages";
        public static final String SORT = "sort";
        public static final String PRODUCT_COUNT = "productCount";
        public static final String PRODUCT_ID = "product_id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE_SOURCE = "image_source";
        public static final String CATEGORY_ID = "category_id";
        public static final String CATEGORY_NAME = "category_name";
        public static final String MANUFACTURER_ID = "manufacturer_id";
        public static final String MANUFACTURER_NAME = "manufacturer_name";
        public static final String SHOPPING_CART = "shoppingCart";
        public static final String UNSORTED = "unsorted";
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
        public static final String[] SAMPLING_PARAMETERS = {MANUFACTURERS, CATEGORIES, MIN_PRICE, MAX_PRICE};
        public static final int DEFAULT_PRODUCT_PER_PAGE = 9;
        public static final int START_PAGE = 1;
    }

    public static class Order {
        public static final String ORDER = "order";
        public static final String ORDERS = "orders";
        public static final String STATUS = "status";
        public static final String STATUS_DESCRIPTION = "status_description";
        public static final String CUSTOMER_ID = "customer_id";
        public static final String SHIPMENT_ADDRESS = "shipment_address";
        public static final String PAYMENT_CARD = "payment_card";
        public static final String ORDERED_PRODUCTS = "ordered_products";
        public static final String ORDER_ID = "order_id";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_MANUFACTURER = "product_manufacturer";
        public static final String PRODUCT_DESCRIPTION = "product_description";
        public static final String PRODUCT_PRICE = "product_price";
        public static final String PRODUCT_COUNT = "product_count";
        public static final String PRODUCT_SUM = "product_sum";
        public static final String TOTAL_SUM = "total_sum";
        public static final String ORDER_CREATED = "Order created";
        public static final String ORDER_CREATED_DESCRIPTION = "Order created and sent to the processing";
    }

    public static class Queries {
        public static final String ADD_USER = "INSERT INTO users(firstName, lastName, email, password) VALUES (?,?,?,?)";
        public static final String GET_USER = "SELECT * FROM users WHERE email=?";
        public static final String GET_ALL_MANUFACTURERS = "SELECT * FROM manufacturers";
        public static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    }

    public static class SQL {
        public static final String SELECT = "SELECT";
        public static final String INSERT_INTO = "INSERT INTO";
        public static final String VALUES = "VALUES";
        public static final String FROM = "FROM";
        public static final String JOIN = "JOIN";
        public static final String WHERE = "WHERE";
        public static final String ORDER_BY = "ORDER BY";
        public static final String LIMIT = "LIMIT";
        public static final String ON = "ON";
        public static final String OR = "OR";
        public static final String AND = "AND";
        public static final String ALL = "*";
        public static final String OPEN_BRACKET = "(";
        public static final String CLOSE_BRACKET = ")";
        public static final String COMMA = ", ";
        public static final String EQUALITY_MARK = "=";
        public static final String VALUE_HOLDER = "=?";
        public static final String LT_EQ = "<=";
        public static final String GT_EQ = ">=";
        public static final String VALUE_INPUT = "?";
        public static final String COUNT = "COUNT(*)";
    }

    public static class Locale {
        public static final String LOCALE = "locale";
        public static final String DEFAULT_LOCALE = "defaultLocale";
        public static final String LOCALES = "locales";
        public static final String SESSION_LOCALE_STORAGE = "sessionLocaleStoring";
        public static final String COOKIE_LOCALE_STORAGE = "cookieLocaleStoring";
        public static final String LOCALE_STORAGE_MODE = "localeStorageMode";
        public static final String LANG = "lang";
    }

    public static class Compression {
        public static final String CONTENT_ENCODING = "Content-Encoding";
        public static final String GZIP = "gzip";
        public static final String ACCEPT_ENCODING = "Accept_Encoding";
    }

    public static class Cache {
        public static final String CACHE_CONTROL = "Cache-control";
        public static final String EXPIRES = "Expires";
        public static final String PRAGMA = "Pragma";
        public static final String NO_CACHE = "No-cache";
    }

    public static class Security {
        public static final String CONFIGURATION = "configuration";
        public static final String SECURITY = "security";
        public static final String CONSTRAINT = "constraint";
        public static final String URL_PATTERN = "url-pattern";
        public static final String ROLE = "role";
    }

}
