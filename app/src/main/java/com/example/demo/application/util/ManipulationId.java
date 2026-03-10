package com.example.demo.application.util;

import java.util.List;

public class ManipulationId {

    public static String manipulationIdentifierUrl (List<String> listIdentifier) {

        if (listIdentifier.isEmpty()) {
            return "url1";
        }

        String idNameFix = "url";
        String idFinalNumber = "";

        for (String id : listIdentifier) {
            idFinalNumber = id.substring(3);
        }

        String toAddId = String.valueOf(Integer.parseInt(idFinalNumber) + 1);
        return idNameFix + toAddId;
    }
}
