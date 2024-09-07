package egovframework.payload;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum SQLExceptionEnum {

	SQL_0("0", "successful_completion"),
	//Class 01 — Warning
	SQL_1000("1000", "warning"),
	SQL_0100C("0100C", "dynamic_result_sets_returned"),
	SQL_1008("1008", "implicit_zero_bit_padding"),
	SQL_1003("1003", "null_value_eliminated_in_set_function"),
	SQL_1007("1007", "privilege_not_granted"),
	SQL_1006("1006", "privilege_not_revoked"),
	SQL_1004("1004", "string_data_right_truncation"),
	SQL_01P01("01P01", "deprecated_feature"),
	//Class 02 — No Data (this is also a warning class per the SQL standard)
	SQL_2000("2000", "no_data"),
	SQL_2001("2001", "no_additional_dynamic_result_sets_returned"),
	//Class 03 — SQL Statement Not Yet Complete
	SQL_3000("3000", "sql_statement_not_yet_complete"),
	//Class 08 — Connection Exception
	SQL_8000("8000", "connection_exception"),
	SQL_8003("8003", "connection_does_not_exist"),
	SQL_8006("8006", "connection_failure"),
	SQL_8001("8001", "sqlclient_unable_to_establish_sqlconnection"),
	SQL_8004("8004", "sqlserver_rejected_establishment_of_sqlconnection"),
	SQL_8007("8007", "transaction_resolution_unknown"),
	SQL_08P01("08P01", "protocol_violation"),
	//Class 09 — Triggered Action Exception
	SQL_9000("9000", "triggered_action_exception"),
	//Class 0A — Feature Not Supported
	SQL_0A000("0A000", "feature_not_supported"),
	//Class 0B — Invalid Transaction Initiation
	SQL_0B000("0B000", "invalid_transaction_initiation"),
	//Class 0F — Locator Exception("Class 0F — Locator Exception", ""),
	SQL_0F000("0F000", "locator_exception"),
	SQL_0F001("0F001", "invalid_locator_specification"),
	//Class 0L — Invalid Grantor("Class 0L — Invalid Grantor", ""),
	SQL_0L000("0L000", "invalid_grantor"),
	SQL_0LP01("0LP01", "invalid_grant_operation"),
	//Class 0P — Invalid Role Specification("Class 0P — Invalid Role Specification", ""),
	SQL_0P000("0P000", "invalid_role_specification"),
	//Class 0Z — Diagnostics Exception("Class 0Z — Diagnostics Exception", ""),
	SQL_0Z000("0Z000", "diagnostics_exception"),
	SQL_0Z002("0Z002", "stacked_diagnostics_accessed_without_active_handler"),
	//Class 20 — Case Not Found("Class 20 — Case Not Found", ""),
	SQL_20000("20000", "case_not_found"),
	//Class 21 — Cardinality Violation("Class 21 — Cardinality Violation", ""),
	SQL_21000("21000", "cardinality_violation"),
	//Class 22 — Data Exception("Class 22 — Data Exception", ""),
	SQL_22000("22000", "data_exception"),
	SQL_2202E("2202E", "array_subscript_error"),
	SQL_22021("22021", "character_not_in_repertoire"),
	SQL_22008("22008", "datetime_field_overflow"),
	SQL_22012("22012", "division_by_zero"),
	SQL_22005("22005", "error_in_assignment"),
	SQL_2200B("2200B", "escape_character_conflict"),
	SQL_22022("22022", "indicator_overflow"),
	SQL_22015("22015", "interval_field_overflow"),
	SQL_2201E("2201E", "invalid_argument_for_logarithm"),
	SQL_22014("22014", "invalid_argument_for_ntile_function"),
	SQL_22016("22016", "invalid_argument_for_nth_value_function"),
	SQL_2201F("2201F", "invalid_argument_for_power_function"),
	SQL_2201G("2201G", "invalid_argument_for_width_bucket_function"),
	SQL_22018("22018", "invalid_character_value_for_cast"),
	SQL_22007("22007", "invalid_datetime_format"),
	SQL_22019("22019", "invalid_escape_character"),
	SQL_2200D("2200D", "invalid_escape_octet"),
	SQL_22025("22025", "invalid_escape_sequence"),
	SQL_22P06("22P06", "nonstandard_use_of_escape_character"),
	SQL_22010("22010", "invalid_indicator_parameter_value"),
	SQL_22023("22023", "invalid_parameter_value"),
	SQL_22013("22013", "invalid_preceding_or_following_size"),
	SQL_2201B("2201B", "invalid_regular_expression"),
	SQL_2201W("2201W", "invalid_row_count_in_limit_clause"),
	SQL_2201X("2201X", "invalid_row_count_in_result_offset_clause"),
	SQL_2202H("2202H", "invalid_tablesample_argument"),
	SQL_2202G("2202G", "invalid_tablesample_repeat"),
	SQL_22009("22009", "invalid_time_zone_displacement_value"),
	SQL_2200C("2200C", "invalid_use_of_escape_character"),
	SQL_2200G("2200G", "most_specific_type_mismatch"),
	SQL_22004("22004", "null_value_not_allowed"),
	SQL_22002("22002", "null_value_no_indicator_parameter"),
	SQL_22003("22003", "numeric_value_out_of_range"),
	SQL_2200H("2200H", "sequence_generator_limit_exceeded"),
	SQL_22026("22026", "string_data_length_mismatch"),
	SQL_22001("22001", "string_data_right_truncation"),
	SQL_22011("22011", "substring_error"),
	SQL_22027("22027", "trim_error"),
	SQL_22024("22024", "unterminated_c_string"),
	SQL_2200F("2200F", "zero_length_character_string"),
	SQL_22P01("22P01", "floating_point_exception"),
	SQL_22P02("22P02", "invalid_text_representation"),
	SQL_22P03("22P03", "invalid_binary_representation"),
	SQL_22P04("22P04", "bad_copy_file_format"),
	SQL_22P05("22P05", "untranslatable_character"),
	SQL_2200L("2200L", "not_an_xml_document"),
	SQL_2200M("2200M", "invalid_xml_document"),
	SQL_2200N("2200N", "invalid_xml_content"),
	SQL_2200S("2200S", "invalid_xml_comment"),
	SQL_2200T("2200T", "invalid_xml_processing_instruction"),
	SQL_22030("22030", "duplicate_json_object_key_value"),
	SQL_22031("22031", "invalid_argument_for_sql_json_datetime_function"),
	SQL_22032("22032", "invalid_json_text"),
	SQL_22033("22033", "invalid_sql_json_subscript"),
	SQL_22034("22034", "more_than_one_sql_json_item"),
	SQL_22035("22035", "no_sql_json_item"),
	SQL_22036("22036", "non_numeric_sql_json_item"),
	SQL_22037("22037", "non_unique_keys_in_a_json_object"),
	SQL_22038("22038", "singleton_sql_json_item_required"),
	SQL_22039("22039", "sql_json_array_not_found"),
	SQL_2203A("2203A", "sql_json_member_not_found"),
	SQL_2203B("2203B", "sql_json_number_not_found"),
	SQL_2203C("2203C", "sql_json_object_not_found"),
	SQL_2203D("2203D", "too_many_json_array_elements"),
	SQL_2203E("2203E", "too_many_json_object_members"),
	SQL_2203F("2203F", "sql_json_scalar_required"),
	SQL_2203G("2203G", "sql_json_item_cannot_be_cast_to_target_type"),
	//Class 23 — Integrity Constraint Violation
	SQL_23000("23000", "integrity_constraint_violation"),
	SQL_23001("23001", "restrict_violation"),
	SQL_23502("23502", "not_null_violation"),
	SQL_23503("23503", "foreign_key_violation"),
	SQL_23505("23505", "unique_violation"),
	SQL_23514("23514", "check_violation"),
	SQL_23P01("23P01", "exclusion_violation"),
	//Class 24 — Invalid Cursor State
	SQL_24000("24000", "invalid_cursor_state"),
	//Class 25 — Invalid Transaction State
	SQL_25000("25000", "invalid_transaction_state"),
	SQL_25001("25001", "active_sql_transaction"),
	SQL_25002("25002", "branch_transaction_already_active"),
	SQL_25008("25008", "held_cursor_requires_same_isolation_level"),
	SQL_25003("25003", "inappropriate_access_mode_for_branch_transaction"),
	SQL_25004("25004", "inappropriate_isolation_level_for_branch_transaction"),
	SQL_25005("25005", "no_active_sql_transaction_for_branch_transaction"),
	SQL_25006("25006", "read_only_sql_transaction"),
	SQL_25007("25007", "schema_and_data_statement_mixing_not_supported"),
	SQL_25P01("25P01", "no_active_sql_transaction"),
	SQL_25P02("25P02", "in_failed_sql_transaction"),
	SQL_25P03("25P03", "idle_in_transaction_session_timeout"),
	//Class 26 — Invalid SQL Statement Name
	SQL_26000("26000", "invalid_sql_statement_name"),
	//Class 27 — Triggered Data Change Violation
	SQL_27000("27000", "triggered_data_change_violation"),
	//Class 28 — Invalid Authorization Specification
	SQL_28000("28000", "invalid_authorization_specification"),
	SQL_28P01("28P01", "invalid_password"),
	//Class 2B — Dependent Privilege Descriptors Still Exist
	SQL_2B000("2B000", "dependent_privilege_descriptors_still_exist"),
	SQL_2BP01("2BP01", "dependent_objects_still_exist"),
	//Class 2D — Invalid Transaction Termination
	SQL_2D000("2D000", "invalid_transaction_termination"),
	//Class 2F — SQL Routine Exception
	SQL_2F000("2F000", "sql_routine_exception"),
	SQL_2F005("2F005", "function_executed_no_return_statement"),
	SQL_2F002("2F002", "modifying_sql_data_not_permitted"),
	SQL_2F003("2F003", "prohibited_sql_statement_attempted"),
	SQL_2F004("2F004", "reading_sql_data_not_permitted"),
	//Class 34 — Invalid Cursor Name
	SQL_34000("34000", "invalid_cursor_name"),
	//Class 38 — External Routine Exception
	SQL_38000("38000", "external_routine_exception"),
	SQL_38001("38001", "containing_sql_not_permitted"),
	SQL_38002("38002", "modifying_sql_data_not_permitted"),
	SQL_38003("38003", "prohibited_sql_statement_attempted"),
	SQL_38004("38004", "reading_sql_data_not_permitted"),
	//Class 39 — External Routine Invocation Exception
	SQL_39000("39000", "external_routine_invocation_exception"),
	SQL_39001("39001", "invalid_sqlstate_returned"),
	SQL_39004("39004", "null_value_not_allowed"),
	SQL_39P01("39P01", "trigger_protocol_violated"),
	SQL_39P02("39P02", "srf_protocol_violated"),
	SQL_39P03("39P03", "event_trigger_protocol_violated"),
	//Class 3B — Savepoint Exception
	SQL_3B000("3B000", "savepoint_exception"),
	SQL_3B001("3B001", "invalid_savepoint_specification"),
	//Class 3D — Invalid Catalog Name
	SQL_3D000("3D000", "invalid_catalog_name"),
	//Class 3F — Invalid Schema Name
	SQL_3F000("3F000", "invalid_schema_name"),
	//Class 40 — Transaction Rollback
	SQL_40000("40000", "transaction_rollback"),
	SQL_40002("40002", "transaction_integrity_constraint_violation"),
	SQL_40001("40001", "serialization_failure"),
	SQL_40003("40003", "statement_completion_unknown"),
	SQL_40P01("40P01", "deadlock_detected"),
	//Class 42 — Syntax Error or Access Rule Violation
	SQL_42000("42000", "syntax_error_or_access_rule_violation"),
	SQL_42601("42601", "syntax_error"),
	SQL_42501("42501", "insufficient_privilege"),
	SQL_42846("42846", "cannot_coerce"),
	SQL_42803("42803", "grouping_error"),
	SQL_42P20("42P20", "windowing_error"),
	SQL_42P19("42P19", "invalid_recursion"),
	SQL_42830("42830", "invalid_foreign_key"),
	SQL_42602("42602", "invalid_name"),
	SQL_42622("42622", "name_too_long"),
	SQL_42939("42939", "reserved_name"),
	SQL_42804("42804", "datatype_mismatch"),
	SQL_42P18("42P18", "indeterminate_datatype"),
	SQL_42P21("42P21", "collation_mismatch"),
	SQL_42P22("42P22", "indeterminate_collation"),
	SQL_42809("42809", "wrong_object_type"),
	SQL_428C9("428C9", "generated_always"),
	SQL_42703("42703", "undefined_column"),
	SQL_42883("42883", "undefined_function"),
	SQL_42P01("42P01", "undefined_table"),
	SQL_42P02("42P02", "undefined_parameter"),
	SQL_42704("42704", "undefined_object"),
	SQL_42701("42701", "duplicate_column"),
	SQL_42P03("42P03", "duplicate_cursor"),
	SQL_42P04("42P04", "duplicate_database"),
	SQL_42723("42723", "duplicate_function"),
	SQL_42P05("42P05", "duplicate_prepared_statement"),
	SQL_42P06("42P06", "duplicate_schema"),
	SQL_42P07("42P07", "duplicate_table"),
	SQL_42712("42712", "duplicate_alias"),
	SQL_42710("42710", "duplicate_object"),
	SQL_42702("42702", "ambiguous_column"),
	SQL_42725("42725", "ambiguous_function"),
	SQL_42P08("42P08", "ambiguous_parameter"),
	SQL_42P09("42P09", "ambiguous_alias"),
	SQL_42P10("42P10", "invalid_column_reference"),
	SQL_42611("42611", "invalid_column_definition"),
	SQL_42P11("42P11", "invalid_cursor_definition"),
	SQL_42P12("42P12", "invalid_database_definition"),
	SQL_42P13("42P13", "invalid_function_definition"),
	SQL_42P14("42P14", "invalid_prepared_statement_definition"),
	SQL_42P15("42P15", "invalid_schema_definition"),
	SQL_42P16("42P16", "invalid_table_definition"),
	SQL_42P17("42P17", "invalid_object_definition"),
	//Class 44 — WITH CHECK OPTION Violation
	SQL_44000("44000", "with_check_option_violation"),
	//Class 53 — Insufficient Resources
	SQL_53000("53000", "insufficient_resources"),
	SQL_53100("53100", "disk_full"),
	SQL_53200("53200", "out_of_memory"),
	SQL_53300("53300", "too_many_connections"),
	SQL_53400("53400", "configuration_limit_exceeded"),
	//Class 54 — Program Limit Exceeded
	SQL_54000("54000", "program_limit_exceeded"),
	SQL_54001("54001", "statement_too_complex"),
	SQL_54011("54011", "too_many_columns"),
	SQL_54023("54023", "too_many_arguments"),
	//Class 55 — Object Not In Prerequisite State
	SQL_55000("55000", "object_not_in_prerequisite_state"),
	SQL_55006("55006", "object_in_use"),
	SQL_55P02("55P02", "cant_change_runtime_param"),
	SQL_55P03("55P03", "lock_not_available"),
	SQL_55P04("55P04", "unsafe_new_enum_value_usage"),
	//Class 57 — Operator Intervention
	SQL_57000("57000", "operator_intervention"),
	SQL_57014("57014", "query_canceled"),
	SQL_57P01("57P01", "admin_shutdown"),
	SQL_57P02("57P02", "crash_shutdown"),
	SQL_57P03("57P03", "cannot_connect_now"),
	SQL_57P04("57P04", "database_dropped"),
	SQL_57P05("57P05", "idle_session_timeout"),
	//Class 58 — System Error (errors external to PostgreSQL itself)
	SQL_58000("58000", "system_error"),
	SQL_58030("58030", "io_error"),
	SQL_58P01("58P01", "undefined_file"),
	SQL_58P02("58P02", "duplicate_file"),
	//Class 72 — Snapshot Failure
	SQL_72000("72000", "snapshot_too_old"),
	//Class F0 — Configuration File Error
	SQL_F0000("F0000", "config_file_error"),
	SQL_F0001("F0001", "lock_file_exists"),
	//Class HV — Foreign Data Wrapper Error (SQL/MED)
	SQL_HV000("HV000", "fdw_error"),
	SQL_HV005("HV005", "fdw_column_name_not_found"),
	SQL_HV002("HV002", "fdw_dynamic_parameter_value_needed"),
	SQL_HV010("HV010", "fdw_function_sequence_error"),
	SQL_HV021("HV021", "fdw_inconsistent_descriptor_information"),
	SQL_HV024("HV024", "fdw_invalid_attribute_value"),
	SQL_HV007("HV007", "fdw_invalid_column_name"),
	SQL_HV008("HV008", "fdw_invalid_column_number"),
	SQL_HV004("HV004", "fdw_invalid_data_type"),
	SQL_HV006("HV006", "fdw_invalid_data_type_descriptors"),
	SQL_HV091("HV091", "fdw_invalid_descriptor_field_identifier"),
	SQL_HV00B("HV00B", "fdw_invalid_handle"),
	SQL_HV00C("HV00C", "fdw_invalid_option_index"),
	SQL_HV00D("HV00D", "fdw_invalid_option_name"),
	SQL_HV090("HV090", "fdw_invalid_string_length_or_buffer_length"),
	SQL_HV00A("HV00A", "fdw_invalid_string_format"),
	SQL_HV009("HV009", "fdw_invalid_use_of_null_pointer"),
	SQL_HV014("HV014", "fdw_too_many_handles"),
	SQL_HV001("HV001", "fdw_out_of_memory"),
	SQL_HV00P("HV00P", "fdw_no_schemas"),
	SQL_HV00J("HV00J", "fdw_option_name_not_found"),
	SQL_HV00K("HV00K", "fdw_reply_handle"),
	SQL_HV00Q("HV00Q", "fdw_schema_not_found"),
	SQL_HV00R("HV00R", "fdw_table_not_found"),
	SQL_HV00L("HV00L", "fdw_unable_to_create_execution"),
	SQL_HV00M("HV00M", "fdw_unable_to_create_reply"),
	SQL_HV00N("HV00N", "fdw_unable_to_establish_connection"),
	//Class P0 — PL/pgSQL Error
	SQL_P0000("P0000", "plpgsql_error"),
	SQL_P0001("P0001", "raise_exception"),
	SQL_P0002("P0002", "no_data_found"),
	SQL_P0003("P0003", "too_many_rows"),
	SQL_P0004("P0004", "assert_failure"),
	//Class XX — Internal Error
	SQL_XX000("XX000", "internal_error"),
	SQL_XX001("XX001", "data_corrupted"),
	SQL_XX002("XX002", "index_corrupted"),


    ;

    private final String code;
    private String message;

    SQLExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 3번
    public static final Map<String, SQLExceptionEnum> map = new HashMap<>();
    static {
        for (SQLExceptionEnum os : SQLExceptionEnum.values()) {
            map.put(os.getCode(), os);
        }
    }
    public static SQLExceptionEnum getCode(String code) {
        return map.get(code);
    }

}