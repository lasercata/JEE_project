package shows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import time.EventTime;
import time.Hour;
import time.HourRange;
import utils.DBManager;
import utils.GeneralDAO;

public class ShowDAOImpl implements GeneralDAO<Show> {
    /**
     * Makes an SQL resquest to the DB to get all the characters playing in the show of id `id`.
     */
    private ArrayList<Character> getCharactersFromShow(int id) {
        Connection connexion = DBManager.getInstance().getConnection();
        ArrayList<Character> charactList = new ArrayList<>();

        try {
            Statement statement = connexion.createStatement();

            String query = "SELECT characters.id, characters.name FROM characters";
            query += " JOIN starring ON starring.idCharacter = characters.id";
            query += " JOIN shows ON starring.idShow = shows.id";
            query += " WHERE shows.id = " + id + ";";

            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                int idCharact = rs.getInt("id");
                String name = rs.getString("name");

                charactList.add(new Character(idCharact, name));
            }

            return charactList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }

        return null;
    }

    private Show readQueryResult(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("titre");
        String day = rs.getString("jour");
        String begTime = rs.getString("heureDebut");
        String endTime = rs.getString("heureFin");
        String location = rs.getString("lieu");

        // Create schedule
        HourRange hr = new HourRange(new Hour(begTime), new Hour(endTime));
        EventTime schedule = new EventTime(day, hr);

        // Get the character list
        ArrayList<Character> charactList = this.getCharactersFromShow(id);

        // Create the show
        Show new_show = new Show(
            id,
            title,
            schedule,
            location,
            charactList
        );

        return new_show;
    }

    @Override
    public ArrayList<Show> getAll() {
        Connection connexion = DBManager.getInstance().getConnection();

        ArrayList<Show> allList = new ArrayList<>();

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select " + Show.sqlFields() + " from " + Show.getTblName() + ";");

            while(rs.next()) {
                Show new_show = this.readQueryResult(rs);
                allList.add(new_show);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }

        return allList;
    }

    @Override
    public Show getById(int id) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("select "+ Show.sqlFields() + " from " + Show.getTblName() +" where id = " + id + ";");

            while(rs.next()) {
                return this.readQueryResult(rs);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
        return null;
    }

    @Override
    public void add(Show new_attraction) {
        Connection connexion = DBManager.getInstance().getConnection();

        try {
            Statement statement = connexion.createStatement();
            statement.executeUpdate("insert into attractions (" + Show.sqlFields() + ") values (" +  new_attraction.toSQL() + ");");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }

    @Override
    public void edit(Show show) {
        Connection connexion = DBManager.getInstance().getConnection();
        try {
            Statement statement = connexion.createStatement();
            String stm = String.format(
                "update %s set titre = '%s', jour = '%s', heureDebut = '%s', heureFin = '%s', lieu = '%s' WHERE id = %d;",
                Show.getTblName(),
                show.getTitre(),
                show.getSchedule().getDay(),
                String.format(
                    "%s:%s",
                    show.getSchedule().getTime().getOpening().getH(),
                    show.getSchedule().getTime().getOpening().getM()
                ),
                show.getLieu(),
                show.getId()
            );
            statement.executeUpdate(stm);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().cleanup(connexion, null, null);
        }
    }
}
