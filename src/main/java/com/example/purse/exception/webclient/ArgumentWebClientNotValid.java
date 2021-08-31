package com.example.purse.exception.webclient;

import com.example.purse.util.I18AbleException;

public class ArgumentWebClientNotValid extends I18AbleException {
	public ArgumentWebClientNotValid(String key, Object... args) {
        super(key, args);
    }
}
