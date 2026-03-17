/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.mail.Authenticator
 *  javax.mail.PasswordAuthentication
 */
package project_login;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class Mail.1
extends Authenticator {
    Mail.1() {
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("{MAIL_USER}", "{MAIL_PASSWORD}");
    }
}
