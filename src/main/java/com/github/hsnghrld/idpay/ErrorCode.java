package com.github.hsnghrld.idpay;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public enum ErrorCode {
    _0(
            "Unknown error ({0})",
            "خطای نامشخص ({0})"
    ),
    _11(
            "User is blocked.",
            "کاربر مسدود شده است."
    ),
    _12(
            "API key not found.",
            "کلید API یافت نشد."
    ),
    _13(
            "Your request has been sent from {ip}. This IP does not match the IPs registered in the web service.",
            "درخواست شما از {ip} ارسال شده است. این IP با IP های ثبت شده در وب سرویس همخوانی ندارد."
    ),
    _14(
            "Your web service is being reviewed or not verified.",
            "وب سرویس شما در حال بررسی است و یا تایید نشده است."
    ),
    _21(
            "The bank account connected to the web service has not been verified.",
            "حساب بانکی متصل به وب سرویس تایید نشده است."
    ),
    _22(
            "Web service not found.",
            "وب سریس یافت نشد."
    ),
    _23(
            "Web service validation failed.",
            "اعتبار سنجی وب سرویس ناموفق بود."
    ),
    _24(
            "The bank account associated with this web service has been disabled.",
            "حساب بانکی مرتبط با این وب سرویس غیر فعال شده است."
    ),
    _31(
            "The id must not be empty.",
            "کد تراکنش (id) نباید خالی باشد."
    ),
    _32(
            "The order_id must not be blank.",
            "شماره سفارش (order_id) نباید خالی باشد."
    ),
    _33(
            "The amount should not be empty.",
            "مبلغ (amount) نباید خالی باشد."
    ),
    _34(
            "The amount must be more than {min-amount} Rials.",
            "مبلغ (amount) باید بیشتر از {min-amount} ریال باشد."
    ),
    _35(
            "The amount must be less than {max-amount} Rials.",
            "مبلغ (amount) باید کمتر از {max-amount} ریال باشد."
    ),
    _36(
            "The amount is more than allowed.",
            "مبلغ (amount) بیشتر از حد مجاز است."
    ),
    _37(
            "The callback must not be empty.",
            "آدرس بازگشت (callback) نباید خالی باشد."
    ),
    _38(
            "Your request has been sent from {domain}. The callback domain does not match the address registered on the web service.",
            "درخواست شما از آدرس {domain} ارسال شده است. دامنه آدرس بازگشت (callback) با آدرس ثبت شده در وب سرویس همخوانی ندارد."
    ),
    _41(
            "The transaction status filter should be an array (list) of allowed statuses in the documentation.",
            "فیلتر وضعیت تراکنش ها می بایست آرایه ای (لیستی) از وضعیت های مجاز در مستندات باشد."
    ),
    _42(
            "The payment date filter should be an array containing the min and max elements of the timestamp type.",
            "فیلتر تاریخ پرداخت می بایست آرایه ای شامل المنت های min و max از نوع timestamp باشد."
    ),
    _43(
            "The settlement date filter should be an array containing the min and max elements of the timestamp type.",
            "فیلتر تاریخ تسویه می بایست آرایه ای شامل المنت های min و max از نوع timestamp باشد."
    ),
    _51(
            "No transaction was created.",
            "تراکنش ایجاد نشد."
    ),
    _52(
            "The query returned no results.",
            "استعلام نتیجه ای نداشت."
    ),
    _53(
            "Payment verification is not possible.",
            "تایید پرداخت امکان پذیر نیست."
    ),
    _54(
            "Payment verification period has elapsed.",
            "مدت زمان تایید پرداخت سپری شده است."
    ),
    ;

    public static final String[] ALL_ERROR_CODES = Arrays.stream(ErrorCode.values()).map(ErrorCode::toString)
            .toArray(String[]::new);

    private final String desc_en;
    private final String desc_fa;

    ErrorCode(String desc_en, String desc_fa) {
        this.desc_en = desc_en;
        this.desc_fa = desc_fa;
    }

    public String getDesc_en() {
        return desc_en;
    }

    public String getDesc_fa() {
        return desc_fa;
    }

    @Override
    public @NotNull String toString() {
        return super.toString().substring(1);
    }

    public static @NotNull ArrayList<String> toString(@NotNull Collection<ErrorCode> statuses) {
        ArrayList<String> statuses_string = new ArrayList<>();
        for (ErrorCode status : statuses)
            statuses_string.add(status.toString());
        return statuses_string;
    }
}