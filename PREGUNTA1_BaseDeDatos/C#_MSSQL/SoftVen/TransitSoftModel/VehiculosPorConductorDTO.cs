using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftVenModel
{
    public class VehiculosPorConductorDTO
    {
        private int? vehiculoId;
        private int? conductorId;
        private DateTime? fechaAdquisicion;

        public VehiculosPorConductorDTO()
        {
            this.VehiculoId = null;
            this.ConductorId = null;
            this.FechaAdquisicion = null;
        }

        public VehiculosPorConductorDTO(int vehiculoId, int conductorId, DateTime fechaAdquisicion)
        {
            this.VehiculoId = vehiculoId;
            this.ConductorId = conductorId;
            this.FechaAdquisicion = fechaAdquisicion;
        }

        public int? VehiculoId { get => vehiculoId; set => vehiculoId = value; }
        public int? ConductorId { get => conductorId; set => conductorId = value; }
        public DateTime? FechaAdquisicion { get => fechaAdquisicion; set => fechaAdquisicion = value; }
    }
}
