package egovframework.payload;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
	/*
	 *
	 	관리자: ADMIN
	 	사회자: 
	 	사용자: USER
	*/
	USER_001(HttpStatus.BAD_REQUEST , "USER_001", "회원가입에 실패하였습니다."),

    JOIN_001(HttpStatus.BAD_REQUEST , "JOIN_001", "학적정보조회 기능 점검중입니다."),

    LOGIN_001(HttpStatus.BAD_REQUEST , "LOGIN_001", "등록된 계정 정보가 없습니다."),
    LOGIN_002(HttpStatus.BAD_REQUEST , "LOGIN_002", "비밀번호가 일치하지 않습니다."),
    LOGIN_003(HttpStatus.BAD_REQUEST , "LOGIN_003", "잘못된 접근입니다."),
    LOGIN_004(HttpStatus.BAD_REQUEST , "LOGIN_004", "웨일스페이스 로그인 실패"),
    LOGIN_005(HttpStatus.BAD_REQUEST , "LOGIN_005", "동일 아이디로 접속하여 로그아웃 됩니다."),
    LOGIN_006(HttpStatus.BAD_REQUEST , "LOGIN_006", "사용할 수 없는 계정입니다."),



    // 500
    COMM_001(HttpStatus.INTERNAL_SERVER_ERROR , "COMM_001", "오류가 발생했습니다. (서버오류)"),
    COMM_002(HttpStatus.NOT_FOUND , "COMM_002", "존재하는 {%s}가 없습니다."),
    COMM_003(HttpStatus.UNAUTHORIZED , "COMM_003", "접근 권한이 없습니다."),
    COMM_004(HttpStatus.BAD_REQUEST , "COMM_004", "올바르지 않은 요청입니다."),
    COMM_005(HttpStatus.BAD_REQUEST , "COMM_005", "올바르지 않은 요청입니다. (Json 파싱에러)"),
    COMM_006(HttpStatus.BAD_REQUEST , "COMM_006", "잘못된 정렬값이 입력 되었습니다. 정렬 할 수 있는 컬럼이 아닙니다."),


    // EvlMng
    HTM101_001(HttpStatus.NOT_FOUND , "HTM101_001", "학교급 코드가 없습니다."),
    HTM106_001(HttpStatus.NOT_FOUND , "HTM106_001", "난이도의 합계를 확인 해주세요."),
    HTM107_001(HttpStatus.NOT_FOUND , "HTM107_001", "초등학교는 학년과 학기를 선택해야 합니다."),
    HTM107_002(HttpStatus.NOT_FOUND , "HTM107_002", "평가명을 입력 해주세요."),
    HTM107_003(HttpStatus.NOT_FOUND , "HTM107_003", "문항 유형의 JsonObject 확인이 필요합니다."),
    HTM107_004(HttpStatus.NOT_FOUND , "HTM107_004", "문항 유형 비율의 합은 100이여야 합니다."),
    HTM107_005(HttpStatus.NOT_FOUND , "HTM107_005", "문항 자동배정에 실패 했습니다."),
    HTM107_006(HttpStatus.NOT_FOUND , "HTM107_006", "단원/차시가 선택 되어야 합니다."),
    HTM107_007(HttpStatus.NOT_FOUND , "HTM107_007", "단원/차시의 Json 확인이 필요합니다."),
    HTM107_008(HttpStatus.NOT_FOUND , "HTM107_008", "평가게시판과 관련된 정보 확인이 필요합니다."),

    HTM114_001(HttpStatus.NOT_FOUND , "HTM114_001", "단원/차시의 Json 확인이 필요합니다."),

    HTM120_001(HttpStatus.NOT_FOUND , "HTM120_001", "단원/차시는 필수 값 입니다."),

    HTM121_001(HttpStatus.NOT_FOUND , "HTM121_001", "잘못된 평가 ID 입니다."),

    HTM122_001(HttpStatus.NOT_FOUND , "HTM122_001", "초등학교는 학년과 학기를 선택해야 합니다."),
    HTM122_002(HttpStatus.NOT_FOUND , "HTM122_002", "평가구분이 선택 되어야 합니다."),
    HTM122_003(HttpStatus.NOT_FOUND , "HTM122_003", "평가명을 입력 해주세요."),
    HTM122_004(HttpStatus.NOT_FOUND , "HTM122_004", "잘못된 평가 ID 입니다."),
    HTM122_005(HttpStatus.NOT_FOUND , "HTM122_005", "설정하신 총 문항수와 배정하신 문항수가 다릅니다."),
    HTM122_006(HttpStatus.NOT_FOUND , "HTM122_006", "문항배정 확정 취소가 필요합니다."),
    HTM122_007(HttpStatus.NOT_FOUND , "HTM122_007", "평가게시판과 관련된 정보 확인이 필요합니다."),

    HTM125_001(HttpStatus.NOT_FOUND , "HTM125_001", "잘못된 평가 ID 입니다."),
    HTM125_002(HttpStatus.NOT_FOUND , "HTM125_002", "삭제된 평가 입니다."),

    HTM126_001(HttpStatus.NOT_FOUND , "HTM126_001", "평가ID와 문항ID가 맞지 않습니다."),

    HTM127_001(HttpStatus.NOT_FOUND , "HTM127_001", "배정 문항 수 확인이 필요합니다."),
    HTM127_002(HttpStatus.NOT_FOUND , "HTM127_002", "임시저장 평가만 확정 하실 수 있습니다."),

    HTM128_001(HttpStatus.NOT_FOUND , "HTM128_001", "내보내기 이력을 모두 삭제해야 문항을 재배정 할 수 있습니다.\n내보내기 이력을 삭제하시겠습니까?"),
    HTM128_002(HttpStatus.NOT_FOUND , "HTM128_002", "응시이력이 있는 평가는 문항 재배정이 불가능합니다."),
    HTM128_003(HttpStatus.NOT_FOUND , "HTM128_003", "문항 배정 확정이 안되어있는 평가입니다."),

    HTM131_001(HttpStatus.NOT_FOUND , "HTM131_001", "내보내려는 평가게시판이 선택되지 않았습니다."),
    HTM131_002(HttpStatus.NOT_FOUND , "HTM131_002", "내보내려는 클래스,평가게시판 확인이 필요합니다."),
    HTM131_003(HttpStatus.NOT_FOUND , "HTM131_003", "삭제된 클래스 입니다."),
    HTM131_004(HttpStatus.NOT_FOUND , "HTM131_004", "비공개된 평가게시판 입니다."),
    HTM131_005(HttpStatus.NOT_FOUND , "HTM131_005", "삭제된 평가게시판 입니다."),

    HTM135_001(HttpStatus.NOT_FOUND , "HTM135_001", "내보내기 이력이 없어야 평가를 삭제 할 수 있습니다.<br>내보내기 이력을 삭제하시겠습니까?"),
    HTM135_002(HttpStatus.NOT_FOUND , "HTM135_002", "응시 이력이 있는 평가로 삭제가 불가합니다.<br>클래스별 평가 목록에서 응시 이력을 확인해 주세요"),
    HTM135_003(HttpStatus.NOT_FOUND , "HTM135_003", "이미 삭제된 평가입니다."),

    HTM142_001(HttpStatus.NOT_FOUND , "HTM142_001", "속한 클래스의 선생님만 조회 가능합니다."),
    HTM142_002(HttpStatus.NOT_FOUND , "HTM142_002", "개별평가가 삭제되거나 정보 확인이 필요합니다."),

    HTM143_001(HttpStatus.NOT_FOUND , "HTM143_001", "클래스에 포함되지 않은 학생입니다."),
    HTM143_002(HttpStatus.NOT_FOUND , "HTM143_002", "이미 응시한 학생은 제외가 불가능 합니다."),
    HTM143_003(HttpStatus.NOT_FOUND , "HTM143_003", "종료된 평가입니다."),

    HTM145_001(HttpStatus.NOT_FOUND , "HTM145_001", "공개/비공개 여부를 선택 해주세요."),
    HTM145_002(HttpStatus.NOT_FOUND , "HTM145_002", "공개/비공개 여부 값을 확인 해주세요."),

    HTM147_001(HttpStatus.NOT_FOUND , "HTM147_001", "입력하신 정보의 확인이 필요합니다."),
    HTM147_002(HttpStatus.NOT_FOUND , "HTM147_002", "응시 이력이 있는 시험 입니다. 평가 기간 변경은 시험 감독 메뉴에서 가능합니다."),

    HTM148_001(HttpStatus.NOT_FOUND , "HTM148_001", "시험 평가 이력이 있어 삭제가 불가합니다.\n(교사 평가 관리 홈 > 시험 감독 메뉴에서 학생의 평가 이력을 재시험으로 삭제해야 평가를 삭제할 수 있습니다.)"),


    // PrcPrb
    HTM800_001(HttpStatus.NOT_FOUND , "HTM800_001", "평가정보 확인이 필요합니다."),
    HTM800_002(HttpStatus.NOT_FOUND , "HTM800_002", "선택하신 평가에서 제외 되었습니다."),
    HTM800_003(HttpStatus.NOT_FOUND , "HTM800_003", "삭제된 평가 입니다."),
    HTM800_004(HttpStatus.NOT_FOUND , "HTM800_004", "삭제된 평가게시판 입니다."),
    HTM800_005(HttpStatus.NOT_FOUND , "HTM800_005", "비노출된 평가게시판 입니다."),
    HTM800_006(HttpStatus.NOT_FOUND , "HTM800_006", "폐쇄된 클래스 입니다."),
    HTM800_007(HttpStatus.NOT_FOUND , "HTM800_007", "성적공개여부가 결정된 클래스 입니다."),
    HTM800_008(HttpStatus.NOT_FOUND , "HTM800_008", "클래스에 제외된 응시자 입니다."),
    HTM800_009(HttpStatus.NOT_FOUND , "HTM800_009", "클래스내의 학생만 응시가 가능합니다."),
    HTM800_010(HttpStatus.NOT_FOUND , "HTM800_010", "종료된 시험 입니다."),
    HTM800_011(HttpStatus.NOT_FOUND , "HTM800_011", "비공개된 시험 입니다."),

    // commQues
    HTM1100_001(HttpStatus.NOT_FOUND , "HTM1100_001", "문항정보 확인이 필요합니다."),
    HTM1100_002(HttpStatus.NOT_FOUND , "HTM1100_002", "시험 타입 확인이 필요합니다."),
    HTM1101_001(HttpStatus.NOT_FOUND , "HTM1101_001", "시험만 답안 저장이 가능합니다."),
    HTM1101_002(HttpStatus.NOT_FOUND , "HTM1101_002", "유효하지 않은 응시자 입니다."),
    HTM1101_003(HttpStatus.NOT_FOUND , "HTM1101_003", "삭제된 답가지 또는 유효하지 않은 문항 입니다."),


    ///////교사 채점 및 결과
    TEA_RST_001(HttpStatus.NOT_FOUND , "TEA_RST_001", "저장할 학생이 없습니다.<br>학생 목록을 확인하세요."),
    TEA_RST_002(HttpStatus.NOT_FOUND , "TEA_RST_002", "문항 정보에 점수가 없습니다."),
    TEA_RST_003(HttpStatus.NOT_FOUND , "TEA_RST_003", "정답을 선택해주세요.<br>"),
    ;

    private final HttpStatus status;
    private final String code;
    @Setter
    private String message;

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static final Map<String, ExceptionEnum> map = new HashMap<>();
    static {
        for (ExceptionEnum os : ExceptionEnum.values()) {
            map.put(os.getCode(), os);
        }
    }
    public static ExceptionEnum getCode(String code) {
        return map.get(code);
    }
}