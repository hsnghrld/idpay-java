package com.github.hsnghrld.idpay;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class GetListOfTransactionsResult {
    private final String getListOfTransactionsResult_String;
    public final Attachment attachment;
    public final ArrayList<Transaction> transactions = new ArrayList<>();

    public GetListOfTransactionsResult(@NotNull JSONObject object) throws JSONException {
        getListOfTransactionsResult_String = object.toString(4);
        attachment = object.has("attachment") && !object.isNull("attachment")
                ? new Attachment(object.getJSONObject("attachment")) : null;
        if (object.has("records") && !object.isNull("records"))
            for (Object record : object.getJSONArray("records"))
                transactions.add(new Transaction(new JSONObject(record.toString())));
    }

    @Override
    public String toString() {
        return getListOfTransactionsResult_String;
    }

    /* STATIC CLASS */

    public static class Attachment {
        private final String attachment_string;
        public final Long page_amount;
        public final String total_amount;
        public final Integer total_count, page_count, current_page;
        public final Long timestamp;

        Attachment(@NotNull JSONObject object) throws JSONException {
            attachment_string = object.toString(4);
            page_amount = object.has("page_amount") && !object.isNull("page_amount") ? object.getLong("page_amount") : null;
            total_amount = object.has("total_amount") && !object.isNull("total_amount") ? object.getString("total_amount") : null;
            total_count = object.has("total_count") && !object.isNull("total_count") ? object.getInt("total_count") : null;
            page_count = object.has("page_count") && !object.isNull("page_count") ? object.getInt("page_count") : null;
            current_page = object.has("current_page") && !object.isNull("current_page") ? object.getInt("current_page") : null;
            timestamp = object.has("timestamp") && !object.isNull("timestamp") ? object.getLong("timestamp") : null;
        }

        @Override
        public String toString() {
            return attachment_string;
        }
    }

}
