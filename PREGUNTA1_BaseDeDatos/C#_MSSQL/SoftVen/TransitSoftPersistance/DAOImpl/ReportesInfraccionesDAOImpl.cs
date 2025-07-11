using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVen.DAOImpl.Util;
using SoftVen.DAOImpl;
using SoftVenModel;
using SoftVenPersistance.DAO;

namespace SoftVenPersistance.DAOImpl
{
    public class ReportesInfraccionesDAOImpl : DAOImplBase, ReportesInfraccionesDAO
    {
        private ReportesInfraccionesDTO reporte;

        public ReportesInfraccionesDAOImpl() : base("EX1_REPORTES_INFRACCIONES")
        {
            this.retornarLlavePrimaria = true;
            this.reporte = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("REPORTE_ID", true, true));
            this.listaColumnas.Add(new Columna("CONDUCTOR_ID", false, false));
            this.listaColumnas.Add(new Columna("PATERNO", false, false));
            this.listaColumnas.Add(new Columna("MATERNO", false, false));
            this.listaColumnas.Add(new Columna("NOMBRES", false, false));
            this.listaColumnas.Add(new Columna("VEHICULO_ID", false, false));
            this.listaColumnas.Add(new Columna("PLACA", false, false));
            this.listaColumnas.Add(new Columna("MARCA", false, false));
            this.listaColumnas.Add(new Columna("MODELO", false, false));
            this.listaColumnas.Add(new Columna("ANHO", false, false));
            this.listaColumnas.Add(new Columna("INFRACCION_ID", false, false));
            this.listaColumnas.Add(new Columna("DESCRIPCION", false, false));
            this.listaColumnas.Add(new Columna("MONTO", false, false));
            this.listaColumnas.Add(new Columna("GRAVEDAD", false, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@CONDUCTOR_ID", this.reporte.ConductorId);
            this.AgregarParametro("@PATERNO", this.reporte.Paterno);
            this.AgregarParametro("@MATERNO", this.reporte.Materno);
            this.AgregarParametro("@NOMBRES", this.reporte.Nombres);
            this.AgregarParametro("@VEHICULO_ID", this.reporte.VehiculoId);
            this.AgregarParametro("@PLACA", this.reporte.Placa);
            this.AgregarParametro("@MARCA", this.reporte.Marca);
            this.AgregarParametro("@MODELO", this.reporte.Modelo);
            this.AgregarParametro("@ANHO", this.reporte.Anho);
            this.AgregarParametro("@INFRACCION_ID", this.reporte.InfraccionId);
            this.AgregarParametro("@DESCRIPCION", this.reporte.Descripcion);
            this.AgregarParametro("@MONTO", this.reporte.Monto);
            this.AgregarParametro("@GRAVEDAD", this.reporte.Gravedad);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.IncluirValorDeParametrosParaInsercion();
            this.AgregarParametro("@REPORTE_ID", this.reporte.ReporteId);
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.AgregarParametro("@REPORTE_ID", this.reporte.ReporteId);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.AgregarParametro("@REPORTE_ID", this.reporte.ReporteId);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.reporte = new ReportesInfraccionesDTO();
            this.reporte.ReporteId = lector.GetInt32(0);
            this.reporte.ConductorId = lector.GetInt32(1);
            this.reporte.Paterno = lector.GetString(2);
            this.reporte.Materno = lector.IsDBNull(3) ? null : lector.GetString(3);
            this.reporte.Nombres = lector.GetString(4);
            this.reporte.VehiculoId = lector.GetInt32(5);
            this.reporte.Placa = lector.GetString(6);
            this.reporte.Marca = lector.GetString(7);
            this.reporte.Modelo = lector.GetString(8);
            this.reporte.Anho = lector.GetInt32(9);
            this.reporte.InfraccionId = lector.GetInt32(10);
            this.reporte.Descripcion = lector.GetString(11);
            this.reporte.Monto = lector.GetDecimal(12);
            this.reporte.Gravedad = lector.GetString(13);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.reporte = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.reporte);
        }

        public int Insertar(ReportesInfraccionesDTO reporte)
        {
            this.reporte = reporte;
            return base.Insertar();
        }

        public int Modificar(ReportesInfraccionesDTO reporte)
        {
            this.reporte = reporte;
            return base.Modificar();
        }

        public int Eliminar(ReportesInfraccionesDTO reporte)
        {
            this.reporte = reporte;
            return base.Eliminar();
        }

        public ReportesInfraccionesDTO ObtenerPorId(int reporteId)
        {
            this.reporte = new ReportesInfraccionesDTO();
            this.reporte.ReporteId = reporteId;
            base.ObtenerPorId();
            return this.reporte;
        }

        public new BindingList<ReportesInfraccionesDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<ReportesInfraccionesDTO> retorno = new BindingList<ReportesInfraccionesDTO>();
            foreach (ReportesInfraccionesDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }
}

