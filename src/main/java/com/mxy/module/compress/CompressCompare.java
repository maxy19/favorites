package com.mxy.module.compress;

import java.io.IOException;

public class CompressCompare {


    public static void main(String[] args) throws IOException {
        String s = "[{\"package\":\"com.qualcomm.qti.qcolor\",\"version\":29},{\"package\":\"com.qualcomm.qti.improvetouch.service\",\"version\":29},{\"package\":\"com.unionpay.tsmservice.mi\",\"version\":22},{\"package\":\"com.mi.liveassistant\",\"version\":451003},{\"package\":\"com.milink.service\",\"version\":12020503},{\"package\":\"com.qti.service.colorservice\",\"version\":1},{\"package\":\"com.qualcomm.qti.simcontacts\",\"version\":1},{\"package\":\"com.bsp.catchlog\",\"version\":29},{\"package\":\"com.sankuai.meituan\",\"version\":1100080203},{\"package\":\"com.qualcomm.uimremoteclient\",\"version\":29},{\"package\":\"com.qualcomm.qti.uceShimService\",\"version\":29},{\"package\":\"vendor.qti.hardware.cacert.server\",\"version\":1},{\"package\":\"com.qualcomm.qti.telephonyservice\",\"version\":29},{\"package\":\"com.qualcomm.qti.performancemode\",\"version\":29},{\"package\":\"vendor.qti.iwlan\",\"version\":1},{\"package\":\"com.baidu.input_mi\",\"version\":527},{\"package\":\"com.qualcomm.uimremoteserver\",\"version\":29},{\"package\":\"com.qti.confuridialer\",\"version\":29},{\"package\":\"com.versa.vanilla\",\"version\":25},{\"package\":\"com.tencent.mm\",\"version\":1860},{\"package\":\"com.wapi.wapicertmanage\",\"version\":29},{\"package\":\"com.qti.qualcomm.datastatusnotification\",\"version\":29},{\"package\":\"com.qualcomm.qti.callfeaturessetting\",\"version\":29},{\"package\":\"com.qualcomm.wfd.service\",\"version\":2},{\"package\":\"com.mi.health\",\"version\":20702},{\"package\":\"com.qti.qualcomm.deviceinfo\",\"version\":29},{\"package\":\"com.mfashiongallery.emag\",\"version\":2020092800},{\"package\":\"org.codeaurora.ims\",\"version\":1},{\"package\":\"com.standardar.service\",\"version\":1},{\"package\":\"com.qualcomm.qti.dynamicddsservice\",\"version\":1},{\"package\":\"com.qualcomm.qcrilmsgtunnel\",\"version\":29},{\"package\":\"com.bsquare.ucdetectservice\",\"version\":29},{\"package\":\"com.qualcomm.qti.services.systemhelper\",\"version\":1},{\"package\":\"com.duokan.phone.remotecontroller\",\"version\":6024},{\"package\":\"com.fido.asm\",\"version\":10},{\"package\":\"com.qti.dpmserviceapp\",\"version\":29},{\"package\":\"com.qti.xdivert\",\"version\":29},{\"package\":\"com.mipay.wallet\",\"version\":3200146},{\"package\":\"com.qti.snapdragon.qdcm_ff\",\"version\":1},{\"package\":\"mark.via\",\"version\":20210331},{\"package\":\"com.qualcomm.qti.qtisystemservice\",\"version\":29},{\"package\":\"com.qualcomm.qti.remoteSimlockAuth\",\"version\":29},{\"package\":\"com.qualcomm.qti.workloadclassifier\",\"version\":29},{\"package\":\"com.wandoujia.phoenix2\",\"version\":19861},{\"package\":\"com.tencent.soter.soterserver\",\"version\":29},{\"package\":\"com.baidu.BaiduMap\",\"version\":1045},{\"package\":\"com.qualcomm.qti.cne\",\"version\":1},{\"package\":\"com.qualcomm.qti.ims\",\"version\":1},{\"package\":\"com.qualcomm.qti.lpa\",\"version\":29},{\"package\":\"com.qualcomm.qti.uim\",\"version\":29},{\"package\":\"com.goodix.fingerprint.baikalsetting\",\"version\":4},{\"package\":\"com.autonavi.minimap\",\"version\":8800},{\"package\":\"com.duokan.reader\",\"version\":621200329},{\"package\":\"com.qualcomm.qti.uimGbaApp\",\"version\":29},{\"package\":\"com.qualcomm.qti.services.secureui\",\"version\":1},{\"package\":\"com.example.misamples\",\"version\":1},{\"package\":\"com.qualcomm.qti.seccamservice\",\"version\":2},{\"package\":\"com.qualcomm.qti.poweroffalarm\",\"version\":29},{\"package\":\"com.taobao.taobao\",\"version\":351},{\"package\":\"com.qualcomm.timeservice\",\"version\":29},{\"package\":\"com.qualcomm.atfwd\",\"version\":29},{\"package\":\"com.qualcomm.embms\",\"version\":1}]";
        System.out.println("压缩前长度:" + s.length());
        long begin1 = System.currentTimeMillis();
        byte[] compress = compress(s.getBytes());
        System.out.println("压缩后长度:" + compress.length);
        //System.out.println("压缩后值:"+new String(compress));
        System.out.println("压缩耗时:" + (System.currentTimeMillis() - begin1) + "ms");

        long begin2 = System.currentTimeMillis();
        byte[] uncompress = uncompress(compress);
        String value = new String(uncompress);
        System.out.println("解压后值:" + value);
        System.out.println("解压后长度:" + uncompress.length);
        System.out.println("解压耗时:" + (System.currentTimeMillis() - begin2) + "ms");
    }

    public static byte[] compress(byte srcBytes[]) throws IOException {
        return org.xerial.snappy.Snappy.compress(srcBytes);
    }

    public static byte[] uncompress(byte[] bytes) throws IOException {
        return org.xerial.snappy.Snappy.uncompress(bytes);
    }
}
