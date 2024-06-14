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
        return new ApiResponse<>(false,"Not Found ID", null);
    }
}
