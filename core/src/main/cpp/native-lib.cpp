//
// Created by Yoga Darma on 9/17/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_yogadarma_core_utils_Keys_baseUrl(JNIEnv *env, jobject object) {
    std::string baseUrl = "https://api.pexels.com/v1/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring

JNICALL
Java_com_yogadarma_core_utils_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string apiKey = "563492ad6f91700001000001f2e2087887354d98874b2910db42c212";
    return env->NewStringUTF(apiKey.c_str());
}