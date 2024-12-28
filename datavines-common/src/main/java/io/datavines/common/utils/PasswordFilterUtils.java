/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.datavines.common.utils;

import io.datavines.common.utils.SensitiveLogUtils;
import io.datavines.common.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordFilterUtils {

    /**
     * deal with sensitive log
     *
     * @param msg msg
     */
    public static String convertPassword(Pattern pwdPattern, final String msg) {

        String tempMsg = msg;

        if (StringUtils.isNotEmpty(tempMsg)) {
            tempMsg = passwordHandler(pwdPattern, tempMsg, false);
        }

        return tempMsg;
    }

    public static String convertPasswordToNULL(Pattern pwdPattern, final String msg) {

        String tempMsg = msg;

        if (StringUtils.isNotEmpty(tempMsg)) {
            tempMsg = passwordHandler(pwdPattern, tempMsg, true);
        }

        return tempMsg;
    }

    /**
     * password regex
     *
     * @param logMsg original log
     */
    private static String passwordHandler(Pattern pwdPattern, String logMsg, boolean convertToNull) {

        Matcher matcher = pwdPattern.matcher(logMsg);
        StringBuffer sb = new StringBuffer(logMsg.length());
        while (matcher.find()) {
            String password = matcher.group();
            String maskPassword = "";
            if (!convertToNull) {
                maskPassword = SensitiveLogUtils.maskDataSourcePwd(password);
            }
            matcher.appendReplacement(sb, maskPassword);
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

}
