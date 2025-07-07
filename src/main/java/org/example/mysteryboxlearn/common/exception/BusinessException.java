package org.example.mysteryboxlearn.common.exception;

public class BusinessException extends RuntimeException {
    BaseEnum resultCode;

    public BusinessException(String msg) {
        super(msg);
        this.resultCode = ResultCode.Fail;
    }

    public BusinessException(BaseEnum resultCode) {
        super(resultCode.getName());
        this.resultCode = resultCode;
    }

    public BusinessException(BaseEnum resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public BaseEnum getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(final BaseEnum resultCode) {
        this.resultCode = resultCode;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BusinessException)) {
            return false;
        } else {
            BusinessException other = (BusinessException)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$resultCode = this.getResultCode();
                Object other$resultCode = other.getResultCode();
                if (this$resultCode == null) {
                    if (other$resultCode != null) {
                        return false;
                    }
                } else if (!this$resultCode.equals(other$resultCode)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BusinessException;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = super.hashCode();
        Object $resultCode = this.getResultCode();
        result = result * 59 + ($resultCode == null ? 43 : $resultCode.hashCode());
        return result;
    }

    public String toString() {
        return "BusinessException(resultCode=" + String.valueOf(this.getResultCode()) + ")";
    }
}
