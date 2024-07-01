package edu.training.web.newsproject.controller.concrete;

public enum CommandName {
    DO_AUTH, DO_REGISTRATION, DO_LOGOUT,

    GO_TO_INDEX_PAGE, GO_TO_REGISTRATION_PAGE, GO_TO_NEWS_PAGE, GO_TO_AUTH_PAGE, GO_TO_PROFILE_PAGE,

    CREATE_NEWS, UPDATE_NEWS, GET_NEWS,

    SET_LOCALE,

    CHANGE_PASSWORD,

    NO_SUCH_COMMAND
}
