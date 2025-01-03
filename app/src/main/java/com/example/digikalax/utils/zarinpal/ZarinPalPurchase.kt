package com.example.digikalax.utils.zarinpal

import android.annotation.SuppressLint


object ZarinPalPurchase {


//    private val listener = object : BillingClientStateListener {
//        override fun onClientServiceDisconnected() {
//            Log.e("123", "BillingClientDisconected")
//        }
//
//        override fun onClientSetupFinished(state: ClientState) {
//        }
//    }
//
//
//    fun purchase(
//        activity: Activity,
//        amount: Long,
//        description: String,
//        onPurchaseCompleted: (String) -> Unit
//    ) {
//
//        val client = ZarinPalBillingClient.newBuilder(activity)
//            .enableShowInvoice()
//            .setListener(listener)
//            .build()
//
//
//        val purchase = Purchase.newBuilder()
//            .asPaymentRequest("", amount, "https://truelearn.ir/", description).build()
//
//
//        client.launchBillingFlow(purchase,
//            object : FutureCompletionListener<Receipt> {
//                override fun onComplete(task: TaskResult<Receipt>) {
//
//                    if (task.isSuccess) {
//
//                        val reciept = task.success
//                        reciept?.transactionID?.let {
//
//
//                            Log.e("444", it)
//                            onPurchaseCompleted(it)
//
//                        }
//
//
//                    } else {
//
//                        task.failure?.printStackTrace()
//                    }
//
//                }
//
//
//            })
//
//
//    }
//
//
    fun fakePurchase(amount:Long,description: String,
                     onPurchaseCompleted:(String)->Unit){




  onPurchaseCompleted(generateRandomString(8))




    }


    @SuppressLint("SuspiciousIndentation")
    private fun generateRandomString(length:Int):String{

  val chars=('a'..'z')+('A'..'Z')+('0'..'9')
        return List(length){chars.random()}.joinToString("")





    }


}