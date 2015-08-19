package com.hy.API;

import com.hy.vo.Message;

public interface Bank {
public Message saveMoney(String CardID ,String password,double money);
public Message geyMoney(String CardID ,String password,double money);
public double queryMoney(String CardID ,String password);
}
