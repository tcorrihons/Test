#include <jni.h>
#include <chrono>

extern "C"
JNIEXPORT void JNICALL
Java_hins_clay_test_MainActivity_nativeFilter(JNIEnv *env, jobject obj, jintArray src, jintArray dst, jint width, jint height) {

    int *in = env->GetIntArrayElements(src, NULL);
    int *out = env->GetIntArrayElements(dst, NULL);

    int greyColor;
    for (int i = 0; i < width * height; i++) {
        greyColor = ((in[i] >> 16 & 0xff) + (in[i] >> 8 & 0xff) + (in[i] & 0xff)) / 3;
        out[i] = (-1) << 24 | greyColor << 16 | greyColor << 8 | greyColor;
    }

    env->ReleaseIntArrayElements(src, in, 0);
    env->ReleaseIntArrayElements(dst, out, 0);
}