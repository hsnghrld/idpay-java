package com.github.hsnghrld.idpay;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public final class Transaction {
    private final String transaction_string;
    public final short status;
    public final String track_id, id, order_id;
    public final int amount;
    public final Wage wage;
    public final long date;
    public final Payer payer;
    public final PaymentDetail payment;
    public final Verify verify;
    public final Settlement settlement;

    public Transaction(@NotNull JSONObject object) throws JSONException {
        transaction_string = object.toString(4);
        status = (short) object.getInt("status");
        track_id = object.getString("track_id");
        id = object.getString("id");
        order_id = object.getString("order_id");
        amount = object.getInt("amount");
        wage = object.has("wage") && !object.isNull("wage") ? new Wage(object.getJSONObject("wage")) : null;
        date = object.getLong("date");
        payer = object.has("payer") && !object.isNull("payer") ? new Payer(object.getJSONObject("payer")) : null;
        payment = new PaymentDetail(object.getJSONObject("payment"));
        verify = new Verify(object.getJSONObject("verify"));
        settlement = object.has("settlement") && !object.isNull("settlement") ? new Settlement(object.getJSONObject("settlement")) : null;
    }

    @Override
    public String toString() {
        return transaction_string;
    }

    /* STATIC CLASSES */

    public static class PaymentDetail {
        private final String paymentDetail_string;
        public final String track_id;
        public final Integer amount;
        public final String card_no;
        public final String hashed_card_no;
        public final Long date;

        PaymentDetail(@NotNull JSONObject object) throws JSONException {
            paymentDetail_string = object.toString(4);
            track_id = object.has("track_id") && !object.isNull("track_id") ? object.getString("track_id") : null;
            amount = object.has("amount") && !object.isNull("amount") ? object.getInt("amount") : null;
            card_no = object.has("card_no") && !object.isNull("card_no") ? object.getString("card_no") : null;
            hashed_card_no = object.has("hashed_card_no") && !object.isNull("hashed_card_no") ? object.getString("hashed_card_no") : null;
            date = object.has("date") && !object.isNull("date") ? object.getLong("date") : null;
        }

        @Override
        public String toString() {
            return paymentDetail_string;
        }
    }

    public static class Verify {
        private final String verify_string;
        public final Long date;

        Verify(@NotNull JSONObject object) throws JSONException {
            verify_string = object.toString(4);
            date = object.has("date") && !object.isNull("date") ? object.getLong("date") : null;
        }

        @Override
        public String toString() {
            return verify_string;
        }
    }

    public static class Wage {
        private final String wage_string;
        public final String by;
        public final String type;
        public final Integer amount;

        Wage(@NotNull JSONObject object) throws JSONException {
            wage_string = object.toString(4);
            by = object.has("by") && !object.isNull("by") ? object.getString("by") : null;
            type = object.has("type") && !object.isNull("type") ? object.getString("type") : null;
            amount = object.has("amount") && !object.isNull("amount") ? object.getInt("amount") : null;
        }

        @Override
        public String toString() {
            return wage_string;
        }
    }

    public static class Payer {
        private final String payer_string;
        public final String name, phone, mail, desc;

        Payer(@NotNull JSONObject object) throws JSONException {
            payer_string = object.toString(4);
            name = object.has("name") && !object.isNull("name") ? object.getString("name") : null;
            phone = object.has("phone") && !object.isNull("phone") ? object.getString("phone") : null;
            mail = object.has("mail") && !object.isNull("mail") ? object.getString("mail") : null;
            desc = object.has("desc") && !object.isNull("desc") ? object.getString("desc") : null;
        }

        @Override
        public String toString() {
            return payer_string;
        }
    }

    public static class Settlement {
        private final String settlement_string;
        public final String track_id;
        public final Integer amount;
        public final Long date;

        Settlement(@NotNull JSONObject object) throws JSONException {
            settlement_string = object.toString(4);
            track_id = object.has("track_id") && !object.isNull("track_id") ? object.getString("track_id") : null;
            amount = object.has("amount") && !object.isNull("amount") ? object.getInt("amount") : null;
            date = object.has("date") && !object.isNull("date") ? object.getLong("date") : null;
        }

        @Override
        public String toString() {
            return settlement_string;
        }
    }

}