package com.github.hsnghrld.idpay;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public final class TransactionFilter {
    public String id = null;
    public String order_id = null;
    public Long amount = null;
    public HashSet<PaymentStatus> statuses = null;
    public String track_id = null;
    public String payment_card_no = null;
    public String payment_hashed_card_no = null;
    public ValueRange payment_date = null;
    public ValueRange settlement_date = null;

    public @NotNull JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        if (id != null)
            object.put("id", id);
        if (order_id != null)
            object.put("order_id", order_id);
        if (amount != null)
            object.put("amount", amount);
        if (statuses != null)
            object.put("status", PaymentStatus.toString(statuses));
        if (track_id != null)
            object.put("track_id", track_id);
        if (payment_card_no != null)
            object.put("payment_card_no", payment_card_no);
        if (payment_hashed_card_no != null)
            object.put("payment_hashed_card_no", payment_hashed_card_no);
        if (payment_date != null)
            object.put("payment_date", new JSONObject().put("min", payment_date.getMinimum() / 1000).put("max", payment_date.getMaximum() / 1000));
        if (settlement_date != null)
            object.put("payment_date", new JSONObject().put("min", settlement_date.getMinimum() / 1000).put("max", settlement_date.getMaximum() / 1000));
        return object;
    }

    public TransactionFilter id(String id) {
        this.id = id;
        return this;
    }

    public TransactionFilter order_id(String order_id) {
        this.order_id = order_id;
        return this;
    }

    public TransactionFilter amount(long amount) {
        this.amount = amount;
        return this;
    }

    public TransactionFilter statuses(PaymentStatus status, PaymentStatus... statuses) {
        ArrayList<PaymentStatus> list = new ArrayList<>();
        list.add(status);
        list.addAll(Arrays.asList(statuses));
        this.statuses = new HashSet<>(list);
        return this;
    }

    public TransactionFilter track_id(String track_id) {
        this.track_id = track_id;
        return this;
    }

    public TransactionFilter payment_card_no(String payment_card_no) {
        this.payment_card_no = payment_card_no;
        return this;
    }

    public TransactionFilter payment_hashed_card_no(String payment_hashed_card_no) {
        this.payment_hashed_card_no = payment_hashed_card_no;
        return this;
    }

    public TransactionFilter payment_date(ValueRange payment_date) {
        this.payment_date = payment_date;
        return this;
    }

    public TransactionFilter settlement_date(ValueRange settlement_date) {
        this.settlement_date = settlement_date;
        return this;
    }

}
