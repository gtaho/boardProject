package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import conn.DBConn;
import vo.ArticleVO;

public class ArticleDAO {

	// 게시 글 등록
	public void insertArticle(ArticleVO art) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into article(art_no, mem_id, board_no, subject, content, write_date)			");
			sql.append("values(ART_NO_SEQ.nextval, ?, ?, ?, ?, sysdate)													");
			ps = conn.prepareStatement(sql.toString());

			ps.setString(1, art.getMemId());
			ps.setInt(2, art.getBoardNo());
			ps.setString(3, art.getSubject());
			ps.setString(4, art.getContent());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	// 게시 글 수정
	public void updateArticle(ArticleVO art) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update article    									");
			sql.append("set subject = ? , content = ?    				");
			sql.append("where board_no = ? and art_no = ?		");
			ps = conn.prepareStatement(sql.toString());

			ps.setString(1, art.getSubject());
			ps.setString(2, art.getContent());
			ps.setInt(3, art.getBoardNo());
			ps.setInt(4, art.getArtNo());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// 게시 글 삭제
	public void deleteArticle(int articleNo) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from reply						");
			sql.append("where art_no = ?						");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, articleNo);

			pstmt.executeUpdate();
			pstmt.close();

			sql.delete(0, sql.length());

			sql.append("delete from article                                 ");
			sql.append("where art_no = ?                                    ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, articleNo);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	// 게시 글 목록조회
	public Vector<Vector<Object>> selectAllArticle(int boardNo) throws SQLException {
		Vector<Vector<Object>> art = new Vector<Vector<Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select art_no, subject, mem_id, to_char(write_date, 'YYYY/MM/DD')  			");
			sql.append("from article    																								");
			sql.append("where board_no = ?    																					");
			sql.append("order by write_date desc                  															");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<Object> a = new Vector<Object>();
				a.addElement(rs.getInt(1));
				a.addElement(rs.getString(2));
				a.addElement(rs.getString(3));
				a.addElement(rs.getString(4));
				a.addElement(false);
				art.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return art;
	}

	// 게시 글 상세조회
	public ArticleVO selectArticle(int artNo) throws SQLException {
		ArticleVO art = new ArticleVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select art_no, mem_id, subject, content, to_char(write_date, 'YYYY/MM/DD')  			");
			sql.append("from article    																											");
			sql.append("where art_no = ?																										");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, artNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				art.setArtNo(rs.getInt(1));
				art.setMemId(rs.getString(2));
				art.setSubject(rs.getString(3));
				art.setContent(rs.getString(4));
				art.setWriteDate(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return art;
	}

	// 게시 글 검색
	public Vector<Vector<Object>> searchArticle(String keyfield, String keyword) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<Vector<Object>> arts = new Vector<Vector<Object>>();

		try {

			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select art_no, subject, mem_id, to_char(write_date, 'YYYY/MM/DD')                  ");
			sql.append("from article                                                   												 	");
			sql.append("where                                                           													");

			if (keyfield.equals("제목")) {
				sql.append("subject = ?                                                    ");
			} else if (keyfield.equals("내용")) {
				sql.append("content = ?                                                    ");
			} else {
				sql.append("mem_id = ?                                                    ");
			}

			sql.append("order by write_date desc                  ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, "%" + keyword + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector<Object> art = new Vector<Object>();
				art.addElement(rs.getInt(1));
				art.addElement(rs.getString(2));
				art.addElement(rs.getString(3));
				art.addElement(rs.getString(4));
				arts.add(art);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return arts;

	}

}