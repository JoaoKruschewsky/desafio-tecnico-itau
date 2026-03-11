package com.example.demo.application.util;

import java.util.List;

import static com.example.demo.application.util.Constants.identifierUrl;

public class ManipulationId {

    public static String manipulationIdentifierUrl (List<String> listIdentifier) {

        if (listIdentifier.isEmpty()) {
            return identifierUrl;
        }

        String idFinalNumber = "";

        for (String id : listIdentifier) {
            idFinalNumber = id.substring(3);
        }

        String toAddId = String.valueOf(Integer.parseInt(idFinalNumber) + 1);
        return identifierUrl + toAddId;
    }
}
