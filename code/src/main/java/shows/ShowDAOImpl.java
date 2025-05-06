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
    private Show readQueryResult(ResultSet rs) throws SQLException {
        int id1 = rs.getInt("id");
        String title = rs.getString("titre");
        String day = rs.getString("jour");
        String begTime = rs.getString("heureDebut");
        String endTime = rs.getString("heureFin");
        String location = rs.getString("lieu");

        // Create schedule
        HourRange hr = new HourRange(new Hour(begTime), new Hour(endTime));
        EventTime schedule = new EventTime(day, hr);

        // Create the show
        Show new_show = new Show(
            GeneralDAO.unused_id(Show.getTblName()),
            title,
            schedule,
            location,
            new ArrayList<Character>()
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
            ResultSet rs = statement.executeQuery("select "+ Show.sqlFields() + " from attractions where id = " + id + ";");

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
