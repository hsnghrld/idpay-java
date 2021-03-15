[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.hsnghrld/idpay-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.hsnghrld/idpay-java)

# JAVA Module for IDPay API
Simple library to use [IDPay] API in your java code

- [Installation](#installation)
- [Configuration](#configuration)
- [Methods](#methods)
  - [createPayment](#createpayment)
  - [verifyPayment](#verifypayment)
  - [inquiryPayment](#inquirypayment)
  - [getListOfTransactions](#getlistoftransactions)
- [Error handling](#error-handling)

## Installation
idpay-java artifact is available in [Maven Central]

## Configuration
`IDPay` instance can be created like this:
```java
IDPay idPay = new IDPay('YOUR_IDPAY_API_KEY');
```
or
```java
IDPay idPay = new IDPay('YOUR_IDPAY_API_KEY', 'SANDBOX');
```
You can go to your dashboard in the [IDPay] and create a new web service in the [My Web Services] section.
After creating the web service, you will receive your API KEY.

To test the API and how it works, you can set the `SANDBOX` parameter value to `true` (boolean). If the value of this
 parameter is `true`, all subsequent processes are created and simulated experimentally. The default value of this
 parameter is `false`.

> Also, in this case, the sensitivity of the website and IP address is not applied in the sent requests, and you can send
 your request from any callback or IP address.

## Methods

### createPayment
Create a new transaction using this method.

```java
CreatePaymentResult result = idPay.createPayment("35365653542545", 500000, "https://example.com");
```
or
```java
CreatePaymentResult result = idPay.createPayment(
    "35365653542545",
    500000,
    "https://example.com",
    "John Doe",
    null,
    null,
    "This is a test."
);
```

|Input Parameter|Type|Required|Description|
|---|---|---|---|
|order_id|String|Yes|Acceptor order number (It's Up to 50 characters long)|
|amount|Integer|Yes|Desired amount in Rials (It must be between 1,000 Rials to 500,000,000 Rials)|
|callback|String|Yes|URL of comeback to site (It's Up to 2048 characters long)|
|name|String|No|Name of payer (It's Up to 255 characters long)|
|phone|String|No|Payer's mobile number (It must be like 9382198592 or 09382198592 or 989382198592)|
|mail|String|No|Payer's email (It's Up to 255 characters long)|
|desc|String|No|Transaction explanation (It's Up to 255 characters long)|

### verifyPayment
Verify a transaction using this method.

> After receiving the information on the acceptor's site and validating the information by the acceptor,
 the acceptor must confirm the transaction to complete the payment systematically and prevent the return of money
 to the payer

```java
Transaction transaction = idPay.verifyPayment("dfsd-fsefe54-dfdsfdff", "35365653542545");
```

|Input Parameter|Type|Required|Description|
|---|---|---|---|
|id|String|Yes|Unique transaction key received at the [create payment](#createpayment)|
|order_id|String|Yes|Recipient's order number sent at the [create payment](#createpayment)|

### inquiryPayment
Get the latest status of a transaction using this method.

```java
Transaction transaction = idPay.inquiryPayment("dfsd-fsefe54-dfdsfdff", "35365653542545");
```

|Input Parameter|Type|Required|Description|
|---|---|---|---|
|id|String|Yes|Unique transaction key received at the [create payment](#createpayment)|
|order_id|String|Yes|Recipient's order number sent at the [create payment](#createpayment)|

### getListOfTransactions
Get your list of transactions using this method.

```java
GetListOfTransactionsResult result = idPay.getListOfTransactions(
    null,
    null,
    new TransactionFilter()
            .statuses(PaymentStatus._100)
            .payment_date(
                    ValueRange.of(
                            new Date().getTime() - (15L * 24 * 3600 * 1000),
                            new Date().getTime()
                    )
            )
);
```

|Input Parameter|Type|Required|Description|
|---|---|---|---|
|page|Integer|No|The Page number that starts with 0 and it is 0 by default|
|page_size|Integer|No|The number of records received per page that by default is the last 25 transactions|
|filter|TransactionFilter|No|The transaction filter|

## Error handling
It's important to properly handle these cases. We recommend to use `try catch` to handle exceptions.

```java
try {
    idPay.createPayment("35365653542545", 500000, "https://example.com");
} catch (ValidationException e) {
    // invalid parameters passed
} catch (NetworkException e) {
    // network error occurred (timeout, respond code not start with 2 or 4, response body is empty)
} catch (ApiException e) {
    // api respond with error
}
```

[Maven Central]: https://maven-badges.herokuapp.com/maven-central/com.github.hsnghrld/idpay-java
[IDPay]: https://idpay.ir/
[My Web Services]: https://panel.idpay.ir/web-services
