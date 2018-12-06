#include <jni.h>
#include <stdio.h>
#include "util_Integrator.h"
#include "./function/JavaFunction.h"
#include "./math/Integrator.h"


JNIEXPORT jdouble JNICALL Java_util_Integrator_integrate
(JNIEnv * env, jobject obj, jobject fct, jdouble a, jdouble b, jdouble eps) {

    double dF =0;
    try {

        JavaFunction f = JavaFunction(env,fct);
        dF = integrate(f,a,b,eps);

   } catch(const char* error) {
        jclass jExcepClazz = env->FindClass("java/lang/ArithmeticException");
        env->ThrowNew(jExcepClazz, error);
    }
    return (dF);
}
