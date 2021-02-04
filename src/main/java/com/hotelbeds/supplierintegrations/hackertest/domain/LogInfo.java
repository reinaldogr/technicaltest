package com.hotelbeds.supplierintegrations.hackertest.domain;

public class LogInfo {

	Long time;
	Integer failuresCount;

	public LogInfo(Long time, Integer failuresCount) {
		this.time = time;
		this.failuresCount = failuresCount;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getFailuresCount() {
		return failuresCount;
	}

	public void setFailuresCount(Integer failuresCount) {
		this.failuresCount = failuresCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((failuresCount == null) ? 0 : failuresCount.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogInfo other = (LogInfo) obj;
		if (failuresCount == null) {
			if (other.failuresCount != null)
				return false;
		} else if (!failuresCount.equals(other.failuresCount))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogInfo [time=" + time + ", failuresCount=" + failuresCount + "]";
	}
}
