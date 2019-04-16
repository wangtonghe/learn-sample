package test.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

/**
 * @author wangtonghe
 * @since 2018/12/13 17:45
 */
public class HttpTest {

    public static void main(String[] args) {

        String url = "http://test.adx.bcadx.com:16999/madx";


        try {
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

            HttpPost httpPost = new HttpPost(url);




        } catch (Exception e) {
            e.printStackTrace();

        }



    }
}
