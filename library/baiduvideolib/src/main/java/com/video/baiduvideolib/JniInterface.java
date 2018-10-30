package com.video.baiduvideolib;

public class JniInterface {
    public static final String TAG = JniInterface.class.getName();
    private static JniInterface jniInterface;

    private native String decode(Context context, String str, String str2);

    private static native String generateToken(Context context, String str, String str2);

    private static native String signTimeStamp(Context context, String str);

    private JniInterface() {
    }

    protected static void init() {
        String str = "video_decrypt";
        System.loadLibrary(str);
    }

    public static JniInterface getInstance(Context context) {
        if (jniInterface == null) {
            jniInterface = new JniInterface();
        }
        JniInterface.init();
        return jniInterface;
    }

    public String decode(Context context, String input, String key) {
        JniInterface.init();
        String decode = decode(VideoApplication.getInstance(), input, key);
        return decode;
    }

    public static String generateToken(String cuid, String timeStamp) {
        JniInterface.init();
        try {
            return JniInterface.generateToken(VideoApplication.getInstance(), cuid, timeStamp);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String signTimeStamp(String timeStamp) {
        String str = null;
        JniInterface.init();
        try {
            return JniInterface.signTimeStamp(Application app, timeStamp);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return str;
        }
    }

    public void testDecrypt() {
        Log.e("SfHandler", "test decrypt");
        Log.e("SfHandler", "testDecrypt:" + decode(, "fgKCvGLh2KIH2qYifVvAp3YyX9iDy@E%rV0mpV1pp9pfvvKZcOpqp@kjTU8frt8ZfVuI2Op9JvTmTBwcfqv2KVB4Dv2w2QjKypFjV9062giNXUv5rq7b#OJRXF9ur4ufyZL%VvgMnE9#cVwJyQUcXKiHntKArgYDXZFBfQpdg6pYR20RpFLvp3kjvfB#yGIgo6JMKqp9J2iTn%EKy6g7KK0NDFgWn6B0yGfwJ@IG0JiWV9L4XqKJf@pmgpiN8pwmmV1JvR7hoEitRZLooXJSJOBsVJf8rv9RVXFbJfTHMtpuDZ7VJKggvOAuD90fXIQZo6p9gVKBvJfoXaryn@8#pKifrE6Oo9I0m%it237mOvK2XZrorZfBtXKkMvftVBwfmJubtbLNVJtXMVIV8dvz4OBHTf08o@i8D@r2%9JJXFQvRqsg5QfDpKrun90CVF94ff75K9uBJfYF8Bvp2Q6LKf0tTvUSDZ7r2#QhvKtm0K7PDVE#X6vpvOKGMI8SnaEJ29fjvzwMDETeiFpMy@0tfQfy8tuDrftjrdj5pVuyXIQun39gc@pCf9Jjv28Pp4TRy3uY2QLofgpz44iyn@Au%9iFrEvpfXKomaiG4Q0NMUK2V9DpDfY2fQuDOB0tTfAimOY%268NDQ9viqBV8dvz4OBHTf08o@i8D@r2%9T9ivu#ydiVM9Krf9g0v2fCDtQso@2O%9BI%fYTT61v2fwI%Kf68EjPffprfaitJVBIO2IPTvDKDVfWtQwR5v7tD4K#nZ6g26D5pfTt%2KKy@vFfQt9RFIIr2Y4XQE2JXu5KJtvn320M3YDpKrungf2XzTi2qfQOKBC#K8mVpw#n@gapZIeTfjOrZ7Znb2if90EOfifVEwVDVf72@pGsFKvJJ1X2gK9%4LN#JtvR3Bin9EhvftyvpTDDV7DX3DdRfTB%9uuDZ7VJKK923wuD9B#yGTsfKLBO3t9nQY9Dbgiy3gI2dIhyEDkm6Kn2KjjXq7hjK74Df1pX9uJvO0@fF9SnaEJ29Kdf6K2#gKHOg7VoUkXfQiyV6QarvK#XODbJX0IstQfDZ7gJKfVvXFjf2fLTJEJffYggXp9DfuG8GsVnOgdKQp68EJWn39gmaQG29f8M99h82wgVO7bt@pU26KSD4EjfVpqvb92vUiHsFJ%26vRtKt98fiDV69yX6i4pV1pcvf2y6fZc@w0f3wsngfeVaj%fdfI%QAZJ2fzDbs#y9wIJZItJvJJoUBVn69p4XY6fKifVEwVX9Yx2qpiKJK4DtvgmK20V6TSTv9eiQgJ231FtO6J8fiGy@i8rODoJKTHMtpWoEB0c@1p2@kan98eTR08fZf5pKBPDE8mJfAV2JpmK@I1pKjpRd802GrjV@LmcUK2Vds0rfwpVQBzO6fWXpw223YbJv8FyETaRJ9gyZ14tZ9DJBiUV6F0rV0mgfK9if8frZ7ZjQt%VVE3v2QhDKt4ff75KdKIvg8IJG2y2VAPpOKSTE2pRF9pyFBSVOkKXKuwrfwMX@KJ299AMtfvJ9A42FA0v4QP#g9HiF6pfKA84dkpnv7opX7OVVjaV3uWK9uuDZ7VJKfPvKruTKT@8RjOo@u7Jfuynfi6Dbggm3gHOVYwTQDSfJt#rzie237mOfLPDVAtrZ7bfQL8sFKWD4gpmVLY#RY1T20Hs@02y@0tfq7BnvLorgYDTVroZdL9f6tkn3f#0vQ%VV19TgTWDt8roqKbX3K6#9BFVa2KnQwoJKi2#ggSn3f#nbT0237TJEIen@gpDfAs2qQzsvfWV6DomVLYtGu6yQTvRZIVy@vz2d7BnIu9DQYRX@r2%dLIsQukyE0Tc6Lbf3w0v2QhDKvso@u7JfumvgBIT9r52vgHpKpp#JrSy9IpnbQeXK8hMULYTV2tX@JppKuTOBBkr4gs26Qj##uNJK0es@IVfqJkfQiWVBfa8fJZDqjaVd0Isvpkfq8Dc9fjXOAaDKT#DJQTfK75OdpB%Ki6Dbspn@gapZINoEsp2V8OyGicf90TiK8V8vDKX3KCtfun5FQuV9i#2EKy2GIN%fTt%2KKy@vFf@YBDFfBTVQ4DZjaJfiQcFtffgpO5dkg29rq2fBfXBQso@2ZpVK9nfi6Dbspn@gapZIeTfjSn3f#2G8Ff90hMUifJ6Aorq7dpKLkjvtVXR2ZnZ6g%4LNJ2tvs@wJf@J3tO7WnvKmV3Q4DZjaJVuBsFtkn3f#M3L923wanKTCTRpX2qu7gfwZJ2BzmR9V2VABJff4DQJSn320nbQ0RXYNM9i2X3sKDVf72@pGsFKWD4sgnZL92bLeTQIv%OL#fKw8tZYBDFfBTVQ4DZjaJVuBsFtkn3f#M3YPvQgsnUiWVIE%n9LBgQuynEiF861y2gw52XrMtU6Wm9Y#mRrJ29toO2tNDVsKDVf72@pGsFKWD4sgnZL92bLeTf0es@wJnqvXfq75DQLBy@rKXQUxgdQ5fF7vo6KyMVpL#Vkan90CX9tn2@uIKXp9D2fInREK2fwapZIeTfjSn3f#nbikf37UsK8NDVsKDVf72QI@cFKVTargrX6g2bYSTFt#iqYKo31S2QtoDFfBTVQ4DZjaJVuBsFtkn3f#M3L923wanKTCTRpXy3uIRqKovKQ9Vv1#2JJ9OQfeVJ2pf2BcmQiGVKvg0Ui4TgTvTXJstQ9mMI8SnaEJ29K92bLeTf0es@IVy@vFf@YBDFfBTVQ4DZjaJXQQfFIuDZ7VJKK923wanKTCTRpXy3KbpVK9nfi6DbgM2f1BJKi2#ggSn3f#nbikf37UsK8NDVsKrqg0V@pdOBYSTfDgnZL92bLeTf0eiqYKoVg84O79p6fBTVQ4rdTX%XLWMvp#fXipcZ7j#XAq%K02XBQgoZuqpXIQnQ9oTBwJfdAugKsivKDSfXrK2vBNJK8FXgtPDVEODVpJ#OK3fFuXnzsgrXLdfbLtXUf7RgQ#yQw84O79pFKoXEpJXQU9fQTHMt8@fViZpqYI2dsbn6ThybL4fKLbpVK9nEiF86DMm6g9OQ0eTfjbm3rK2vBcvdYNM9i2X3sKDVfWXfunMtpvVQA#m3L9%4L1T2THMV0#ffkFf@YBDtfmDVkJrvj9Z9KBsQukDgJ0c@pCf9JxRK02XBQgo@u7JfuorE0mX4g2y3gI2VINXggSDZ8D2GQG4@BN0UI2n@UgX9LP#99@O98kr4gs26Qj##uNJK0es@IV23J3tffBDQLBTVARrVFupfKosFtkn3f#M3L92dFjRfT@8R04y9Y5KOggyE8IDbAfm3AuJK0tTfjSn3f#nbikf90NRQ8qrVgvTZpJ#OK3f6KWD4sgnZL92bLN8FfeivQVoV1Uvq7WnvKmV3Q4DZjaJVuBsFtvR9T#jqLdf3w0#QQeDtQso@2ZpVK9nfi6Dbspn@25RZI1pKjWn37Vn6Ip29toO2tNDVsKDVf72@pGsvLV8twvnZ6g26D5pfTt%2KKy@vFf@YWJFKoXEpJDZjaJVu9ctf42V8O5dLjp@1bnUpfVa0OodfyO9Lx2JTbrbg02vjoOqQNJKjSn3f#nbikf37Nj992m@smDVss2Q6pOBYSTfDgnZL92bLeTf0es@IVy@vzJKfytJfA8VAZTOjopfpnOB8kn3f#M3L923wanKTCTRpXoOuo2@KbRKizmJLXy3voOqQNJKjSn3f#nbikf37UsK8NDVgiVOf7%Zp3jfTWTpwmmV1J2bLeTf0es@IVy@Yv2Qfn4BTBTVQ4DZjaJVuBsf0Ao37ZcOwptKJxT9B2X#pn2@fQKQL6#K86V#QV2V2oJZI2pgDSDO8r2%Qk4ZBIO2IPTvDKDVf72q0@fF9SnaEJ29K92bLeTQTt%2KKy@vFf@YWrvuIXUp8TfUxg9JJ0tufn9B0cOpqpfgj%OTPrpEJoOtZJVKrnE8FmJ1JfdAugOQeyQJPn3rgm%rst3BIO2IPTvrcXQLHfQuDOB0SnaEJ29ggvJKfrFtXcV0Ky@1eXftW8v7Fy@iXX9U#ZKgJfzpSrZAKJZpQ2dsbnUK2pfIgnQYQKdggJf7zr6wymXAB2QifJXjeoU6Km%if29toO2tPn@EpXQ1svXKkMvftX9jVm920#4LN8QIS%dYnnKkztQfyVBTDTVujrdFLf9KWjvp4fgBp5qwKf9g0v2fCXfgj", "426b2900ad9dd8b3a7eb83301f07fcb0"));
    }
}