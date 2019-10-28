package com.kartik.input.bean;

public class JvmHealthCheck {
private long jvmHeapThreshold;
private long jvmPermGenThreshold;
private long jvmGCCountThreshold;
/**
 * @return the jvmHeapThreshold
 */
public long getJvmHeapThreshold() {
	return jvmHeapThreshold;
}
/**
 * @param jvmHeapThreshold the jvmHeapThreshold to set
 */
public void setJvmHeapThreshold(long jvmHeapThreshold) {
	this.jvmHeapThreshold = jvmHeapThreshold;
}
/**
 * @return the jvmPermGenThreshold
 */
public long getJvmPermGenThreshold() {
	return jvmPermGenThreshold;
}
/**
 * @param jvmPermGenThreshold the jvmPermGenThreshold to set
 */
public void setJvmPermGenThreshold(long jvmPermGenThreshold) {
	this.jvmPermGenThreshold = jvmPermGenThreshold;
}
/**
 * @return the jvmGCCountThreshold
 */
public long getJvmGCCountThreshold() {
	return jvmGCCountThreshold;
}
/**
 * @param jvmGCCountThreshold the jvmGCCountThreshold to set
 */
public void setJvmGCCountThreshold(long jvmGCCountThreshold) {
	this.jvmGCCountThreshold = jvmGCCountThreshold;
}
}
