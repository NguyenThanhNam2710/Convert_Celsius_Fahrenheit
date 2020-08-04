package com.example.lab6_soap;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;
//lấy dữ liệu trả về
public class WebserviceCall {

    private static final SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);

        return envelope;
    }

    private static final String TAG = WebserviceCall.class.getSimpleName();

    public static String callWSThreadSoapPrimitive(String strURL, String
            strSoapAction, SoapObject request) {
        try {
            StringBuffer result;
            SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);
            HttpTransportSE ht = new HttpTransportSE(strURL);
            ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
            ht.debug = true;
            ht.call(strSoapAction, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            result = new StringBuffer(response.toString());
            Log.e(TAG, "result: " + result.toString());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}