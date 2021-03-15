package com.github.hsnghrld.idpay;

import com.github.hsnghrld.idpay.exception.ApiException;
import com.github.hsnghrld.idpay.exception.NetworkException;
import com.github.hsnghrld.idpay.exception.ValidationException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class IDPay {

    public static final String BASE_URL = "https://api.idpay.ir/";
    public static final String API_VERSION = "v1.1";
    private static final OkHttpClient HTTP_CLIENT;

    //    private final String apiKey;
    private final Headers headers;

    static {
        HTTP_CLIENT = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> HTTP_CLIENT.connectionPool().evictAll()));
    }

    public IDPay(String apiKey) {
        this(apiKey, false);
    }

    public IDPay(String apiKey, boolean sandbox) {
//        this.apiKey = apiKey;
        this.headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Accept", "application/json")
                .add("X-API-KEY", apiKey)
                .add("X-SANDBOX", sandbox + "")
                .build();
    }

    /**
     * Create a new payment
     *
     * @param order_id Acceptor order number (required, it's Up to 50 characters long)
     * @param amount   Desired amount in Rials (required, it must be between 1,000 Rials to 500,000,000 Rials)
     * @param callback URL of comeback to site (required, it's Up to 2048 characters long)
     * @return response as an instance of {@link CreatePaymentResult}
     * @throws ValidationException when there is problem in input parameters
     * @throws NetworkException    when network error occurred
     * @throws ApiException        when api respond with error
     */
    public CreatePaymentResult createPayment(@NotNull String order_id, @NotNull Integer amount, @NotNull String callback)
            throws ValidationException, NetworkException, ApiException {
        return createPayment(order_id, amount, callback, null, null, null, null);
    }

    /**
     * Create a new payment
     *
     * @param order_id Acceptor order number (required, it's Up to 50 characters long)
     * @param amount   Desired amount in Rials (required, it must be between 1,000 Rials to 500,000,000 Rials)
     * @param callback URL of comeback to site (required, it's Up to 2048 characters long)
     * @param name     Name of payer (optional, it's Up to 255 characters long)
     * @param phone    Payer's mobile number (optional, it must be like 9382198592 or 09382198592 or 989382198592)
     * @param mail     Payer's email (optional, it's Up to 255 characters long)
     * @param desc     Transaction explanation (optional, it's Up to 255 characters long)
     * @throws ValidationException when there is problem in input parameters
     * @throws NetworkException    when network error occurred
     * @throws ApiException        when api respond with error
     */
    public CreatePaymentResult createPayment(@NotNull String order_id, @NotNull Integer amount, @NotNull String callback, String name,
                                             String phone, String mail, String desc)
            throws ValidationException, NetworkException, ApiException {
        if (order_id == null || amount == null || callback == null)
            throw new ValidationException("order_id and amount and callback are required.");
        if (order_id.length() > 50)
            throw new ValidationException("order_id must be a maximum of 50 characters long.");
        if (amount < 1_000 || amount > 500_000_000)
            throw new ValidationException("amount must be between 1,000 to 500,000,000.");
        if (callback.length() > 255)
            throw new ValidationException("callback must be a maximum of 255 characters long.");
        JSONObject body = new JSONObject()
                .put("order_id", order_id)
                .put("amount", amount)
                .put("callback", callback);
        if (name != null)
            if (name.length() > 255)
                throw new ValidationException("name must be a maximum of 255 characters long.");
            else
                body.put("name", name);
        if (phone != null)
            if (!phone.matches("^([9][8]|[0])?([9][\\d]{9})$"))
                throw new ValidationException("phone must be like 9382198592 or 09382198592 or 989382198592.");
            else
                body.put("phone", phone);
        if (mail != null)
            if (mail.length() > 255)
                throw new ValidationException("mail must be a maximum of 255 characters long.");
            else if (!mail.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$"))
                throw new ValidationException("mail is not valid.");
            else
                body.put("mail", mail);
        if (desc != null)
            if (desc.length() > 255)
                throw new ValidationException("desc must be a maximum of 255 characters long.");
            else
                body.put("desc", desc);

        return commonPost(
                RequestBody.create(
                        MediaType.parse("application/json"),
                        body.toString()
                ),
                API_VERSION + "/payment",
                CreatePaymentResult.class
        );
    }

    /**
     * After receiving the information on the acceptor's site and validating the information by the acceptor,
     * the acceptor must confirm the transaction to complete the payment systematically and prevent the return of money
     * to the payer
     *
     * @param id       Unique transaction key received at the {@link IDPay#createPayment}
     * @param order_id Recipient's order number sent at the {@link IDPay#createPayment}
     * @return response as an instance of {@link Transaction}
     * @throws ValidationException when there is problem in input parameters
     * @throws NetworkException    when network error occurred
     * @throws ApiException        when api respond with error
     */
    public Transaction verifyPayment(@NotNull String id, @NotNull String order_id) throws ValidationException, NetworkException, ApiException {
        if (id == null || order_id == null)
            throw new ValidationException("id and order_id are required.");

        return commonPost(
                RequestBody.create(
                        MediaType.parse("application/json"),
                        new JSONObject()
                                .put("id", id)
                                .put("order_id", order_id)
                                .toString()
                ),
                API_VERSION + "/payment/verify",
                Transaction.class
        );
    }

    /**
     * Inquire about the latest payment status
     *
     * @param id       Unique transaction key received at the {@link IDPay#createPayment}
     * @param order_id Recipient's order number sent at the {@link IDPay#createPayment}
     * @return response as an instance of {@link Transaction}
     * @throws ValidationException when there is problem in input parameters
     * @throws NetworkException    when network error occurred
     * @throws ApiException        when api respond with error
     */
    public Transaction inquiryPayment(@NotNull String id, @NotNull String order_id) throws ValidationException, NetworkException, ApiException {
        if (id == null || order_id == null)
            throw new ValidationException("id and order_id are required.");

        return commonPost(
                RequestBody.create(
                        MediaType.parse("application/json"),
                        new JSONObject()
                                .put("id", id)
                                .put("order_id", order_id)
                                .toString()
                ),
                API_VERSION + "/payment/inquiry",
                Transaction.class
        );
    }

    /**
     * Get your list of transactions
     *
     * @param page      The Page number that starts with 0 and it is 0 by default
     * @param page_size The number of records received per page that by default is the last 25 transactions
     * @param filter    Optional transaction filter
     * @return response as an instance of {@link Transaction}
     * @throws NetworkException when network error occurred
     * @throws ApiException     when api respond with error
     */
    public GetListOfTransactionsResult getListOfTransactions(Integer page, Integer page_size, TransactionFilter filter)
            throws NetworkException, ApiException {
        return commonPost(
                RequestBody.create(
                        MediaType.parse("application/json"),
                        filter != null ? filter.toJSONObject().toString() : "{}"
                ),
                API_VERSION + "/payment/transactions" + "?" + "page=" + (page != null ? page : "0")
                        + "&" + "page_size=" + (page_size != null ? page_size : "5"),
                GetListOfTransactionsResult.class
        );
    }

    /* PRIVATE METHODS */

    private <T> T commonPost(RequestBody requestBody, String path, Class<T> clazz)
            throws NetworkException, ApiException {
        try (Response response = HTTP_CLIENT.newCall(
                new Request.Builder()
                        .url(BASE_URL + path)
                        .headers(headers)
                        .post(requestBody)
                        .build()
        ).execute()) {
            return handleResponse(response, clazz);
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }

    private <T> @Nullable T handleResponse(@NotNull Response response, Class<T> clazz)
            throws NetworkException, ApiException {
        String responseBody_string;
        JSONObject responseBody;
        try {
            if (response.body() == null) throw new NetworkException("Response body is null.");
            else responseBody_string = response.body().string().trim();
            if (responseBody_string.isEmpty()) throw new NetworkException("Response body is empty.");
            else responseBody = new JSONObject(responseBody_string);
            if (responseBody.length() == 0) throw new NetworkException("Response body is empty json.");
        } catch (IOException e) {
            throw new NetworkException(e);
        }

        try {
            if (response.isSuccessful())
                return clazz.cast(clazz.getConstructor(JSONObject.class).newInstance(responseBody));
            else if (response.code() / 100 == 4) throw new ApiException(responseBody.get("error_code").toString());
            else throw new NetworkException("Response code is " + response.code() + ".");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
