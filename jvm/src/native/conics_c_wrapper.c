#include "com_github_nadavwr_cspice_package_binding__.h"
#include <SpiceUsr.h>

/*
 * Class:     com_github_nadavwr_cspice_package_binding__
 * Method:    conics_c_wrapper
 * Signature: ([DD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_com_github_nadavwr_cspice_package_00024binding_00024_conics_1c_1wrapper
  (JNIEnv *env, jobject obj, jdoubleArray jElts, jdouble et)
{
    double* elts = (*env)->GetDoubleArrayElements(env, jElts, 0);
    jdoubleArray jState = (*env)->NewDoubleArray(env, 6);
    double* state = (*env)->GetDoubleArrayElements(env, jState, 0);

    conics_c(elts, et, state);

    (*env)->ReleaseDoubleArrayElements(env, jElts, elts, JNI_ABORT);
    (*env)->ReleaseDoubleArrayElements(env, jState, state, 0);
    return jState;
}
