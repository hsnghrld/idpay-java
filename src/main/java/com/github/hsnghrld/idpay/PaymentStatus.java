package com.github.hsnghrld.idpay;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public enum PaymentStatus {
    _1("Payment not made", "پرداخت انجام نشده است"),
    _2("Payment failed", "پرداخت ناموفق بوده است"),
    _3("An error has occurred", "خطا رخ داده است"),
    _4("Blocked", "بلوکه شده"),
    _5("Return to payer", "برگشت به پرداخت کننده"),
    _6("Systematically returned", "برگشت خورده سیستمی"),
    _7("Cancel payment", "انصراف از پرداخت"),
    _8("Moved to payment gateway", "به درگاه پرداخت منتقل شد"),
    _10("Awaiting payment confirmation", "در انتظار تایید پرداخت"),
    _100("Payment is approved", "پرداخت تایید شده است"),
    _101("Payment has already been approved", "پرداخت قبلا تایید شده است"),
    _200("Deposited to the recipient", "به دریافت کننده واریز شد"),
    ;

    public static final String[] ALL_PAYMENT_STATUSES = Arrays.stream(PaymentStatus.values())
            .map(PaymentStatus::toString).toArray(String[]::new);

    private final String desc_en;
    private final String desc_fa;

    PaymentStatus(String desc_en, String desc_fa) {
        this.desc_en = desc_en;
        this.desc_fa = desc_fa;
    }

    public String getDesc_fa() {
        return desc_fa;
    }

    public String getDesc_en() {
        return desc_en;
    }

    @Override
    public @NotNull String toString() {
        return super.toString().substring(1);
    }

    public static @NotNull ArrayList<String> toString(@NotNull Collection<PaymentStatus> statuses) {
        ArrayList<String> statuses_string = new ArrayList<>();
        for (PaymentStatus status : statuses)
            statuses_string.add(status.toString());
        return statuses_string;
    }
}