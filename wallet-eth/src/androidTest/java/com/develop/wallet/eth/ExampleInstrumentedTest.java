package com.develop.wallet.eth;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        query("0x7Fd5a69AE7b6BE9deFF600a419a41dd381991b84");
    }



    public static void query(String address) {
        // 获取版本信息
        WalletManager.getClientVersion();
        // ethBalance 以太坊余额
        WalletManager.getEthBalance(address);
        // noce
        WalletManager.getNonce(address);
        // name
        WalletManager.getTokenName(address);
        // symbol
        WalletManager.getTokenSymbol(address);
        // decimals
        WalletManager.getTokenDecimals(address);
        // totalsupply
        WalletManager.getTokenTotalSupply(address);
        // token balance 代币余额
        WalletManager.getTokenBalance(address);
    }


}
