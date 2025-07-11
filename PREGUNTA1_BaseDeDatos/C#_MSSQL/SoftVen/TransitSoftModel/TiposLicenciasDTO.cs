using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class TiposLicenciasDTO
    {
        private int? tipoLicenciaId;
        private string nombre;
        private string descripcion;

        public TiposLicenciasDTO()
        {
            this.TipoLicenciaId = null;
            this.Nombre = null;
            this.Descripcion = null;
        }

        public TiposLicenciasDTO(int tipoLicenciaId, string nombre, string descripcion)
        {
            this.TipoLicenciaId = tipoLicenciaId;
            this.Nombre = nombre;
            this.Descripcion = descripcion;
        }

        public int? TipoLicenciaId { get => tipoLicenciaId; set => tipoLicenciaId = value; }
        public string Nombre { get => nombre; set => nombre = value; }
        public string Descripcion { get => descripcion; set => descripcion = value; }
    }
}
