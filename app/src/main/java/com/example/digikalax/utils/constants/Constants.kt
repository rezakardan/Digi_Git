package com.example.digikalax.utils.constants

object Constants {


    const val BASE_URL = "https://truelearn-digikala.iran.liara.run/api/"

const val TIMEOUT=60L

    const val PING_INTERVAL = 3L
    const val NAMED_PING = "named_ping"




   const val API_KEY = "EB43556E671B925B9C98E74643BCA"

    const val X_API_KEY="x-api-key"

    const val LANG="lang"
    const val FA="fa"

 const val SHOPPING_CART_TABLE = "shopping_cart"
 const val FAVORITE_LIST_TABLE = "favorite_list_table"
 const val DATABASE_NAME = "digikala_db"



    const val DIGIJET_URL = "https://www.digikalajet.com/user/address"
    const val AUCTION_URL = "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
    const val DIGIPAY_URL = "https://www.mydigipay.com/"
    const val PINDO_URL = "https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"
    const val SHOPPING_URL = "https://www.digikala.com/landings/village-businesses/?promo_name=boomi-landing&promo_position=circle_badge"
    const val GIFT_CARD_URL = "https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"
    const val DIGIPLUS_URL = "https://www.digikala.com/plus/landing/"
    const val MORE_URL = "https://www.digikala.com/mehr/"


    const val DIGI_FAQ = "https://www.digikala.com/faq/"
    const val DIGI_PRIVACY = "https://www.digikala.com/page/privacy/"
    const val DIGI_TERMS = "https://www.digikala.com/page/terms/"
    const val DIGI_TURLEARN = "https://truelearn.ir/"
    const val DIGI_BUG = "https://www.digikala.com/page/bug-report/"
    const val DIGI_SCORE = "https://cafebazaar.ir/app/com.digikala"

    const val DIGI_WALLET = "https://www.mydigipay.com/"
    const val DIGI_CLUB = "https://www.digikala.com/digiclub/"
    const val TURLEARN_CONTACT_US = "https://truelearn.ir/contact/"

    //Session
    const val SESSION_AUTH_DATA = "session_auth_data"
    const val USER_TOKEN_DATA = "user_token_data"

    const val USER_TOKEN="user_token"

    const val PURCHASE_URL = "https://api.zarinpal.com/pg/v4/payment/"
    const val ZARINPAL_PAYMENT_URL = "https://www.zarinpal.com/pg/StartPay/"
    const val TIMEOUT_IN_SECOND: Long = 60
    var USER_ID = "USER_ID"
    const val USER_ID_DATA = "user_id_data"
    var USER_NAME = "USER_NAME"
    const val USER_NAME_DATA = "user_name_data"

    var USER_PHONE = "USER_PHONE"
   var USER_PHONE_REFRESH = "user_phone_refresh"
    var USER_PHONE_DATA = "user_phone_data"

    var USER_TOKEN_REFRESH = "user_token_refresh"
    var USER_ID_REFRESH = "user_id_refresh"


    var USER_PASSWORD_REFRESH = "user_password_refresh"

   const val USER_PASSWORD_DATA = "user_password_data"

    const val ZARINPAL_MERCHANT_ID = "ce7101df-cb08-41f8-a20b-b21995173d8f"

    const val PRODUCT_COMMENTS = "PRODUCT_COMMENTS"
    const val USER_COMMENTS = "USER_COMMENTS"






    var isFromPurchase = false
    var afterPurchaseUrl = ""
    var purchaseOrderId = ""
    var purchasePrice = ""


}