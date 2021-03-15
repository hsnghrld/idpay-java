package com.github.hsnghrld.idpay;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public final class CreatePaymentResult {
    private final String createPaymentResult_string;
    public final String link, id;

    public CreatePaymentResult(@NotNull JSONObject object) throws JSONException {
        createPaymentResult_string = object.toString(4);
        link = object.has("link") && !object.isNull("link") ? object.getString("link") : null;
        id = object.has("id") && !object.isNull("id") ? object.getString("id") : null;
    }

    @Override
    public String toString() {
        return createPaymentResult_string;
    }
}
