#include <jni.h>
#include <stdio.h>
#include "de_lab4inf_wrb_Differentiator.h"
#include "./function/JavaFunction.h"
#include "./math/Differentiator.cpp"


#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     util_Differentiator
 * Method:    integrate
 * Signature: (Lde/lab4inf/wrb/Function;DDD)D
 */
JNIEXPORT jdouble JNICALL Java_de_lab4inf_wrb_Differentiator_differentiate
(JNIEnv * env, jobject obj, jobject fct, jdouble x, jdouble eps) {
    double dF =0;
    try {
        JavaFunction f = JavaFunction(env,fct);
        dF = differentiate(f,x,eps);
    } catch(const char* error) {
        jclass jExcepClazz = env->FindClass("java/lang/ArithmeticException");
        env->ThrowNew(jExcepClazz, error);
    }
    return (dF);
}

#ifdef __cplusplus
}
#endif
