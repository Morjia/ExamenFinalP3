using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using System.Threading.Tasks;
using SoftVen.DAOImpl;
using TransitSoftDomain;
using MySql.Data.MySqlClient;

namespace SoftVenPersistance.DAOImpl
{
    internal class ReporteInfraccionDAOImpl : DAOImplBase
    {
        private ReporteInfraccion reporteInfraccion;
        public ReporteInfraccionDAOImpl() : base("EX1_REPORTES_INFRACCIONES")
        {
            this.retornarLlavePrimaria = true;
            this.reporteInfraccion = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Clear();
        }

        public int insertarReporteInfraccion(int conductorId, string paterno, string materno, string nombres, int vehiculoId, string placa, string marca, string modelo, int anho, int infraccionId, string descripcion, double monto, string gravedad)
        {
            reporteInfraccion = new ReporteInfraccion(conductorId, paterno, materno, nombres, vehiculoId, placa, marca, modelo, anho, infraccionId, descripcion, monto, gravedad);

            object reporteId = this.EjecutarProcedimientoConParametroOut(
                "INSERTAR_REGISTRO_INFRACCION",
                cmd => {
                    cmd.CommandType = System.Data.CommandType.StoredProcedure;

                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_CONDUCTOR_ID", conductorId));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_PATERNO"     , paterno));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_MATERNO"     , materno));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_NOMBRES"     , nombres));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_VEHICULO_ID" , vehiculoId));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_PLACA"       , placa));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_MARCA"       , marca));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_MODELO"      , modelo));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_ANHO"        , anho));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_INFRACCION_ID", infraccionId));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_DESCRIPCION" , descripcion));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_MONTO"       , monto));
                    cmd.Parameters.Add(new MySql.Data.MySqlClient.MySqlParameter("_GRAVEDAD"    , gravedad));
                },
                System.Data.DbType.Int32,
                "_REPORTE_ID"
            );
            return (int) reporteId;
        }
    }
}
