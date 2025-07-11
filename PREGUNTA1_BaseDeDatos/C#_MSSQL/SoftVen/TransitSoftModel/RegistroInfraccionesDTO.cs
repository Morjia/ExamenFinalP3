using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class RegistroInfraccionesDTO
    {
        private DateTime? fecha;
        private int? vehiculoId;
        private int? conductorId;
        private int? infraccionId;

        public RegistroInfraccionesDTO()
        {
            this.Fecha = null;
            this.VehiculoId = null;
            this.ConductorId = null;
            this.InfraccionId = null;
        }

        public RegistroInfraccionesDTO(DateTime fecha, int vehiculoId, int conductorId, int infraccionId)
        {
            this.Fecha = fecha;
            this.VehiculoId = vehiculoId;
            this.ConductorId = conductorId;
            this.InfraccionId = infraccionId;
        }

        public DateTime? Fecha { get => fecha; set => fecha = value; }
        public int? VehiculoId { get => vehiculoId; set => vehiculoId = value; }
        public int? ConductorId { get => conductorId; set => conductorId = value; }
        public int? InfraccionId { get => infraccionId; set => infraccionId = value; }
    }
}
