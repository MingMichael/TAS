package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.dao.QuestionDao;
import com.tas.db.DBPool;

public class QuestionDaoImpl  implements QuestionDao {
   
	@Override
	public List<ProgramProblem> getProgramProblemByLanguage(int courseId  ,int offset, int fetch) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 
		String sql="SELECT   [programProblemId],T_ProgramProblem.[courseId],[chapter]"
		+",[title] ,[text],[source],[spendTime],[languageId],T_Language.language, T_Course.courseName,"
		+"T_Chapter.chapterName, answer, "
		+" ROW_NUMBER() OVER(ORDER BY [chapter] asc) AS num "
		 
		+ "FROM [db_exam].[dbo].[T_ProgramProblem],T_Chapter,T_Course,T_Language  "
		
		+"where T_ProgramProblem.languageId=T_Language.ID "
		+"  and T_ProgramProblem.courseId=T_Course.courseId"
	    +" and T_ProgramProblem.chapter=T_Chapter.chapterId"
      +"  and T_Course.courseId=?"
      +" ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY "
      ;
      List< ProgramProblem > programproblemList= new ArrayList<ProgramProblem>();
      ProgramProblem pp;
      
      ResultSet rs=null; 
      
      try {
		rs=dbpool.doQueryRS(sql, new Object[]{courseId,offset,fetch});
		while(rs.next()){
			pp = new ProgramProblem();
			pp.setProgramProblemId(rs.getInt("programProblemId"));
			pp.setCourseId(rs.getInt("courseId"));
			pp.setChapterId(rs.getInt("chapter"));
			
			pp.setTitle(rs.getString("title"));
			pp.setText(rs.getString("text"));
			pp.setScource(rs.getString("source"));
			pp.setSpendTime(rs.getInt("spendTime"));
			pp.setLanguageId(rs.getInt("languageId"));
			pp.setLanguage(rs.getString("language"));
			pp.setCourseName(rs.getString("courseName"));
			pp.setChapterName(rs.getString("chapterName"));
			pp.setAnswer(rs.getString("answer"));
			programproblemList.add(pp);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		dbpool.close();
	}
		return programproblemList;
	}

	@Override
	public List<ProgramProblem> getProgramProblemByCourseAndChapter(int courseId, int chapterId, int offset,
			int fetch) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 
		String sql="SELECT   [programProblemId],T_ProgramProblem.[courseId],[chapter]"
		+",[title] ,[text],[source],[spendTime],[languageId],T_Language.language, T_Course.courseName,"
		+"T_Chapter.chapterName, answer, "
		+" ROW_NUMBER() OVER(ORDER BY [chapter] asc) AS num "
		 
		+ "FROM [db_exam].[dbo].[T_ProgramProblem],T_Chapter,T_Course,T_Language  "
		
		+"where T_ProgramProblem.languageId=T_Language.ID "
		+"  and T_ProgramProblem.courseId=T_Course.courseId"
	    +" and T_ProgramProblem.chapter=T_Chapter.chapterId"
      +"  and T_Course.courseId=? and T_Chapter.chapterId=? " 
      +" ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY "
      ;
      List< ProgramProblem > programproblemList= new ArrayList<ProgramProblem>();
      ProgramProblem pp;
      
      ResultSet rs=null; 
      
      try {
		rs=dbpool.doQueryRS(sql, new Object[]{courseId,chapterId,offset,fetch});
		while(rs.next()){
			pp = new ProgramProblem();
			pp.setProgramProblemId(rs.getInt("programProblemId"));
			pp.setCourseId(rs.getInt("courseId"));
			pp.setChapterId(rs.getInt("chapter"));
			
			pp.setTitle(rs.getString("title"));
			pp.setText(rs.getString("text"));
			pp.setScource(rs.getString("source"));
			pp.setSpendTime(rs.getInt("spendTime"));
			pp.setLanguageId(rs.getInt("languageId"));
			pp.setLanguage(rs.getString("language"));
			pp.setCourseName(rs.getString("courseName"));
			pp.setChapterName(rs.getString("chapterName"));
			pp.setAnswer(rs.getString("answer"));
			programproblemList.add(pp);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		dbpool.close();
	}
		return programproblemList;
	}

	@Override
	public int getAllProgramProblem(int courseId) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 int totalProgram=0;
		String sql="select count(*) as totalProgram from T_ProgramProblem where courseId=?";
		 ResultSet rs=null; 
		 try {
			rs=dbpool.doQueryRS(sql, new Object[]{courseId});
			if(rs.next())
			totalProgram = rs.getInt("totalProgram");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		return totalProgram;
	}

	@Override
	public int insertProgramProblem(ProgramProblem pp) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		int programProblemId=-1;
		String sql="SET NOCOUNT ON insert into T_ProgramProblem(courseId,chapter,title,[text],source,spendTime,languageId,answer)"
		+"values(?,?,?,?,?,?,?,?);  select @@identity  as programProblemId ";
		 ResultSet rs=null; 
		 try {
			 int x=0;
			 rs=dbpool.doQueryRS(sql, new Object[]{pp.getCourseId(),pp.getChapterId(),pp.getTitle(),pp.getText(),
						pp.getScource(),pp.getSpendTime(),pp.getLanguageId(),pp.getAnswer()});
				 
				if(rs.next())
					programProblemId = rs.getInt("programProblemId");
				System.out.println(programProblemId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dbpool.close();
			}
		return programProblemId;
	}

	@Override
	public int getProgramProblemNumByCourseAndChapter(int courseId, int chapterId) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 int totalProgram=0;
		String sql="select count(*) as totalProgram from T_ProgramProblem where courseId=? and chapter=?";
		 ResultSet rs=null; 
		 try {
			rs=dbpool.doQueryRS(sql, new Object[]{courseId,chapterId});
			if(rs.next())
			totalProgram = rs.getInt("totalProgram");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		return totalProgram;
	}

	@Override
	public int delProgramProblemById(int programProblemId) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 int result=0;
		String sql="delete from T_ProgramProblem where programProblemId=?";
		 
		 try {
			result=dbpool.doUpdate(sql, new Object[]{programProblemId});
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		return result;
	}

	@Override
	public int updateProgramProblem(ProgramProblem pp) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 
		String sql="update T_ProgramProblem set courseId=?,chapter=?,title=?,[text]=?,source=?,spendTime=?,languageId=?,answer=? "
				+ "where programProblemId=?";
		 int rs=0; 
		 try {
			  
			 rs=dbpool.doUpdate(sql, new Object[]{pp.getCourseId(),pp.getChapterId(),pp.getTitle(),pp.getText(),
						pp.getScource(),pp.getSpendTime(),pp.getLanguageId(),pp.getAnswer(),pp.getProgramProblemId()});
				 
				 
			 
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dbpool.close();
			}
		return rs;
	}

 

}
