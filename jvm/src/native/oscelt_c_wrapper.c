#include "com_github_nadavwr_cspice_package_binding__.h"
#include <SpiceUsr.h>

/*
 * Class:     com_github_nadavwr_cspice_package_binding__
 * Method:    oscelt_c_wrapper
 * Signature: ([DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_com_github_nadavwr_cspice_package_00024binding_00024_oscelt_1c_1wrapper
  (JNIEnv *env, jobject obj, jdoubleArray jState, jdouble et, jdouble mu)
{
    double* state = (*env)->GetDoubleArrayElements(env, jState, 0);
    jdoubleArray jElts = (*env)->NewDoubleArray(env, 6);
    double* elts = (*env)->GetDoubleArrayElements(env, jElts, 0);

    oscelt_c(state, et, mu, elts);

    (*env)->ReleaseDoubleArrayElements(env, jState, state, JNI_ABORT);
    (*env)->ReleaseDoubleArrayElements(env, jElts, elts, 0);
    return jElts;
}

