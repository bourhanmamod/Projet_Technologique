#pragma  version (1)
#pragma  rs  java_package_name(com.android.rssample)

uchar4  RS_KERNEL  HsvToRgb(uchar4  in) {
    uchar4 h = in.s0;
    uchar4 s = in.s1;
    uchar4 v = in.s2;
    uchar4 alpha = in.s3;

    uchar4 t = (h / 60) % 6;
    uchar4 f = (h / 60) - t;
    uchar4 l = v * (1 - s);
    uchar4 m =  (v * (1 - (f - hsv[1])));
    uchar4 n = v * (1 - (1 - f) * hsv[1]);

        if (t == 0) {
            return  rsPackColorTo8888(v, n, l,alpha);
        } else if (t == 1) {
            return  rsPackColorTo8888(m, v, l,alpha);
        } else if (t == 2) {
            return  rsPackColorTo8888(l, v, n, alpha);
        } else if (t == 3) {
            return  rsPackColorTo8888(l, m, v,alpha);
        } else if (t == 4) {
            return  rsPackColorTo8888(n, l, v, alpha);
        }
        return  rsPackColorTo8888(v, l, m,alpha);
    }
  
    return  rsPackColorTo8888(v, l, m, alpha);
}