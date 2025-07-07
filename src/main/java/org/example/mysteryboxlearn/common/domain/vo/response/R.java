package org.example.mysteryboxlearn.common.domain.vo.response;

import org.example.mysteryboxlearn.common.exception.BaseEnum;
import org.example.mysteryboxlearn.common.exception.ResultCode;

import java.util.Objects;

public class R<T> {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T result;

    public R() {
    }

    private R(T result, Integer code, String msg) {
        this.result = result;
        this.code = code;
        this.msg = msg;
    }

    private R(T result, BaseEnum resultCode) {
        this.result = result;
        this.msg = resultCode.getName();
        this.code = resultCode.getCode();
    }

    public static R<String> ok() {
        return new R(ResultCode.Success.getName(), ResultCode.Success);
    }

    public static <T> R<T> ok(T data) {
        return new R(data, ResultCode.Success);
    }

    public static R<String> fail(BaseEnum resultCode) {
        return new R((Object) null, resultCode);
    }

    public static R<String> fail(BaseEnum resultCode, String msg) {
        return new R((Object) null, resultCode.getCode(), msg);
    }

    public boolean isSuccess() {
        return Objects.equals(ResultCode.Success.getCode(), this.getCode());
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getResult() {
        return this.result;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setResult(final T result) {
        this.result = result;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof R)) {
            return false;
        } else {
            R<?> other = (R) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof R;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getCode();
        return "R(code=" + var10000 + ", msg=" + this.getMsg() + ", result=" + String.valueOf(this.getResult()) + ")";
    }
}

