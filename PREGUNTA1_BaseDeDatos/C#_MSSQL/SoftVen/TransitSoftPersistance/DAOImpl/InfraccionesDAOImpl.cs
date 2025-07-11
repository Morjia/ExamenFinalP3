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
    public class InfraccionesDAOImpl : DAOImplBase, InfraccionesDAO
    {
        private InfraccionesDTO infraccion;

        public InfraccionesDAOImpl() : base("EX1_INFRACCIONES")
        {
            this.retornarLlavePrimaria = true;
            this.infraccion = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("INFRACCION_ID", true, true));
            this.listaColumnas.Add(new Columna("DESCRIPCION", false, false));
            this.listaColumnas.Add(new Columna("MONTO_MULTA", false, false));
            this.listaColumnas.Add(new Columna("GRAVEDAD", false, false));
            this.listaColumnas.Add(new Columna("PUNTOS", false, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@DESCRIPCION", this.infraccion.Descripcion);
            this.AgregarParametro("@MONTO_MULTA", this.infraccion.MontoMulta);
            this.AgregarParametro("@GRAVEDAD", this.infraccion.Gravedad);
            this.AgregarParametro("@PUNTOS", this.infraccion.Puntos);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.AgregarParametro("@DESCRIPCION", this.infraccion.Descripcion);
            this.AgregarParametro("@MONTO_MULTA", this.infraccion.MontoMulta);
            this.AgregarParametro("@GRAVEDAD", this.infraccion.Gravedad);
            this.AgregarParametro("@PUNTOS", this.infraccion.Puntos);
            this.AgregarParametro("@INFRACCION_ID", this.infraccion.InfraccionId);
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.AgregarParametro("@INFRACCION_ID", this.infraccion.InfraccionId);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.AgregarParametro("@INFRACCION_ID", this.infraccion.InfraccionId);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.infraccion = new InfraccionesDTO();
            this.infraccion.InfraccionId = lector.GetInt32(0);
            this.infraccion.Descripcion = lector.GetString(1);
            this.infraccion.MontoMulta = lector.GetDecimal(2);
            this.infraccion.Gravedad = lector.GetString(3);
            this.infraccion.Puntos = lector.GetInt32(4);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.infraccion = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.infraccion);
        }

        public int Insertar(InfraccionesDTO infraccion)
        {
            this.infraccion = infraccion;
            return base.Insertar();
        }

        public int Modificar(InfraccionesDTO infraccion)
        {
            this.infraccion = infraccion;
            return base.Modificar();
        }

        public int Eliminar(InfraccionesDTO infraccion)
        {
            this.infraccion = infraccion;
            return base.Eliminar();
        }

        public InfraccionesDTO ObtenerPorId(int infraccionId)
        {
            this.infraccion = new InfraccionesDTO();
            this.infraccion.InfraccionId = infraccionId;
            base.ObtenerPorId();
            return this.infraccion;
        }

        public new BindingList<InfraccionesDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<InfraccionesDTO> retorno = new BindingList<InfraccionesDTO>();
            foreach (InfraccionesDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }
}
