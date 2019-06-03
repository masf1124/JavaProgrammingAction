package com.algorithm.common;

/**
 * @program: com.algorithm.common
 * @author: mashifei
 * @create: 2019-06-03-16
 */
public class ParseURL {

    public static String parseURL(String url){
        String domainName = null;

        String serverName = url;
        if(null==serverName ||serverName.equals("")){
            return null;
        }else{
            serverName = serverName.toLowerCase();

            serverName = serverName.substring(8);
            final int end  = serverName.indexOf("/");
            serverName = serverName.substring(0,end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if(len>3){
                domainName = "."+domains[len-3]+"."+domains[len-2]+"."+domains[len-1];
            }else if(len<=3 && len>1){
                domainName = "."+domains[len-2]+"."+domains[len-1];
            }else{
                domainName = serverName;
            }
        }
        if(domainName!=null && domainName.indexOf(":")>0 ){
            String[] arr = domainName.split("\\:");
            domainName = arr[0];
        }
        return domainName;
    }

    public static void main(String[] args) {
        String url = "https://www.cnblogs.com/fanshuyao/p/7181855.html?utm_source=itdadao&utm_medium=referral";
        String domainName = parseURL(url);
        System.out.println(domainName);
    }
}
