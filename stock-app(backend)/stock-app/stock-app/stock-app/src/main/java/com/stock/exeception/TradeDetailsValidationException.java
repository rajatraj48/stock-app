package com.stock.exeception;

import java.io.Serializable;

public class TradeDetailsValidationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public TradeDetailsValidationException(String message) {
        super(message);
    }
    
    public TradeDetailsValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}


