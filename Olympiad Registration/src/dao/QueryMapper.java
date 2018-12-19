package dao;

public interface QueryMapper {
	public static final String RETRIVE_ALL_QUERY="select * from participant_details order by registration_number";
	public static final String GET_FEE_QUERY="select b.fields,b.fee from participant_details a join olympiad_exams b on a.fields=b.fields where registration_number=?";
	public static final String INSERT_QUERY="insert into participant_details values(registrationnum_seq.nextval,?,?,?,?,?)";
    public static final String Cancellation_Query="select name from participant_details where registration_number=?";
    public static final String Deletion_Query="delete from participant_details where registration_number=?";

}
