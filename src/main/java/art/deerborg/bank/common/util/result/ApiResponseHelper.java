package art.deerborg.bank.common.util.result;

import org.springframework.http.HttpStatus;

public class ApiResponseHelper {
    public static <T> ApiResponse <T> CREATE(T data){
        return new ApiResponse<>(true,"Created customer",data);
    }

    public static <T> ApiResponse <T> UPDATED(T data){
        return new ApiResponse<>(true,"Updated",data);
    }

    public static <T> ApiResponse <T> OK(T data){
        return new ApiResponse<>(true,"Success",data);
    }

    public static <T> ApiResponse <T> NOT_FOUND_ID(){
        return new ApiResponse<>(false,"Not Found ID");
    }

    public static <T> ApiResponse <T> INVALID_BALANCE(){
        return new ApiResponse<>(false,"Invalid Balance");
    }
    public static <T> ApiResponse <T> INSUFFICIENT_BALANCE(){
        return new ApiResponse<>(false,"Insufficient Balance");
    }
    public static <T> ApiResponse <T> ACTIVE_ACCOUNT(){
        return new ApiResponse<>(false,"Account is already active");
    }
    public static <T> ApiResponse <T> IBAN_CONFLICT(){
        return new ApiResponse<>(false,"Iban belongs to you.");
    }
    public static <T> ApiResponse <T> NOT_FOUND_IBAN(){
        return new ApiResponse<>(false,"Iban not found");
    }
}
