package com.learn.BOMServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryDB {

    public static final List<OutPaqCDA> OUTPAQCDA = new ArrayList<>();
    public static final List<Response> RESPONSES = new ArrayList<>();

    public static class OutPaqCDA {
        public int id;
        public String bankAccount;
        public String folioNumber;
        public Date dateOfSent;
        public Date dateOfReceive;

        public OutPaqCDA(int id, String bankAccount, String folioNumber, Date dateOfSent, Date dateOfReceive) {
            this.id = id;
            this.bankAccount = bankAccount;
            this.folioNumber = folioNumber;
            this.dateOfSent = dateOfSent;
            this.dateOfReceive = dateOfReceive;
        }
    }

    public static class Response {
        public int bomTxnId;
        public String account;
        public String folio;
        public Date dateOfReceive;
        public String statusOfTxn;
        public String bankCode;

        public Response(int bomTxnId, String account, String folio, Date dateOfReceive, String statusOfTxn, String bankCode) {
            this.bomTxnId = bomTxnId;
            this.account = account;
            this.folio = folio;
            this.dateOfReceive = dateOfReceive;
            this.statusOfTxn = statusOfTxn;
            this.bankCode = bankCode;
        }
    }
}
